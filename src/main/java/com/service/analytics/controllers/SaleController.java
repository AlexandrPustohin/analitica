package com.service.analytics.controllers;

import com.service.analytics.dto.ProductDTO;
import com.service.analytics.dto.SaleDTO;
import com.service.analytics.services.saleservice.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SaleController {
    @Autowired
    SaleServiceImpl saleService;

    @RequestMapping(value =  {"/","/index"} , method = RequestMethod.GET)
    public String table (Model model) {
        model.addAttribute("total", 0 );
        return "index";
    }
    //получение суммы продаж за день
    @RequestMapping(value =  "/getTotalForDay" , method = RequestMethod.GET)
    public String getTotal (@RequestParam("day") String day, Model model) throws ParseException {
        //получаем сумму, форматируем (для красоты) и отправляем в модель
        String totalSumma = new DecimalFormat( "###,###.##" ).format(saleService.getTotalForDay(day));
        model.addAttribute("total", totalSumma);
        return "index";
    }
    //получение и вывод списка продаж
    //TODO - сделать пагинацию
    @RequestMapping(value = "/salelist", method = RequestMethod.GET)
    public String saleList(Model model){
        List<SaleDTO> saleDTOList= saleService.getAllSale();
        model.addAttribute("sales", saleDTOList);
        return "sales";
    }
    //получение списка лучших в продаже товаров
    @RequestMapping(value = "/getBestForCard", method = RequestMethod.GET)
    public String getBestForCard(@RequestParam("card_number") String cardNumber, Model model){

        Map<String, Double> productDTOList= new HashMap<>();
        //проверим введеный номер карты
        if (cardNumber!="")
            try {
                productDTOList = saleService.getBestForCard(Long.parseLong(cardNumber));
            }catch (NumberFormatException ex){
                ex.printStackTrace();
            }
        model.addAttribute("products", productDTOList);
        return "bestproducts";
    }
    //список продуктов по карте и дате
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getProducts(@RequestParam("id") String id,       Model model){
        //получаем список из сервиса
        List<ProductDTO> productDTOList = saleService.getProductsByIdSale(Long.parseLong(id));
        model.addAttribute("products", productDTOList);

        return "products";
    }

}
