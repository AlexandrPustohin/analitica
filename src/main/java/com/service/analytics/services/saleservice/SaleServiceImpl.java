package com.service.analytics.services.saleservice;

import com.service.analytics.dto.ProductDTO;
import com.service.analytics.dto.SaleDTO;
import com.service.analytics.factory.ProductFactory;
import com.service.analytics.factory.SaleFactory;
import com.service.analytics.model.Sale;
import com.service.analytics.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Transactional
public class SaleServiceImpl implements SaleService{
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    SaleFactory saleFactory;
    @Autowired
    ProductFactory productFactory;
    public SaleServiceImpl() {
    }

    //сохранение одной записи продажи в БД
    @Override
    public void saveSale(Sale sale) {
        saleRepository.saveAndFlush(sale);
    }
    //сохранение списка записей продаж
    @Override
    public void saveAll(List<SaleDTO> saleDTOS){
        //переведем в из DTO в модель
        List<Sale> saleList = saleFactory.createListSales(saleDTOS);
        //и сохраняем через стрим в БД
        saleList.stream().forEach(sale -> saveSale(sale));
    }
    @Override
    //получить все продажи
    //вот тут надо с учетом бы пагинации - список может быть очень большим
    public List<SaleDTO> getAllSale (){
        List<Sale> saleList = saleRepository.findAll();//сам список
        return saleFactory.createListSaleDTO(saleList);//переводим в DTO и возвращаем
    }
    //получение суммы продаж за день
    @Override
    public double getTotalForDay(String day) throws ParseException {
        if(day.isEmpty()|| day==null) return 0;//отсекаем пустую дату
        List<SaleDTO> saleDTOList = getAllSale();//лист продаж
        //переводим дату в нужный формат
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date temp = sdf.parse(day);
        //получаем следующий день
        Date nextDay = sdf.parse(sdf.format(new Date(temp.getTime()+24*3600*1000)));
        double total = 0;
        //получаем сумму. Можно сделать через стрим. Но правильней запросом к БД
        for (SaleDTO saleDTO: saleDTOList) {
            if(saleDTO.getDate().getTime()>=temp.getTime() && saleDTO.getDate().getTime()<nextDay.getTime()){
                for (ProductDTO product:saleDTO.getProducts()) {
                    total+=product.getPrice()* product.getCount();
                }
            }
        }
        return total;
    }
    //не используется
    @Override
    public double getSummCard(SaleDTO saleDTO){
        return saleDTO.getProducts().stream()
                .map(productDTO -> productDTO.getPrice()* productDTO.getCount())
                .mapToDouble(total -> total).sum();
    }
    //получение списка покупок для информации о продаже
    @Override
    public List<ProductDTO> getProductsByIdSale(long id ){
        List<ProductDTO> productDTOList = new ArrayList<>();
        //непосредственное получение продажи
        SaleDTO saleDTO = saleFactory.fromSaleToDTO(saleRepository.findById(id));
        if (saleDTO!=null){
            return saleDTO.getProducts() ;
        }
        //если ничего нет - возвращаем список с пустым продуктом иначе фронт ругается
        productDTOList.add(new ProductDTO());
        return productDTOList;
    }


    //три лучших продаваемы (или покупаемых) товара карты
    @Override
    public Map<String, Double> getBestForCard(long card_number) {
        //получаем все продажи по карте
        List<SaleDTO> saleDTOList = saleFactory.createListSaleDTO(saleRepository.findAllByCardNumber(card_number));
        //получаем Map со списком
        //сначала получаем список всех продуктов, группируем по наименованию и суммируем по количеству
        //далее сортируем, берем первые три и в конце создаем LinkedHashMap
        //можно запросом к БД.
        Map<String, Double> productDTOList = saleDTOList.stream()
                .flatMap(saleDTO -> saleDTO.getProducts().stream())
                .collect(Collectors.groupingBy(ProductDTO::getName,
                        Collectors.summingDouble(ProductDTO::getCount)))
        .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed()).limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new))
                ;


        return productDTOList;
    }
}
