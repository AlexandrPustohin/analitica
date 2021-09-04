package com.service.analytics.factory;

import com.service.analytics.dto.SaleDTO;
import com.service.analytics.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleFactory {
    @Autowired
    ProductFactory productFactory;
    public Sale fromDTOtoSale(SaleDTO saleDTO){
        return Sale.builder()
                .Id(saleDTO.getId())
                .card_number(saleDTO.getCard_number())
                .date(saleDTO.getDate())
                .products(productFactory.createProductList(saleDTO.getProducts())).build();
    }
    public List<Sale> createListSales(List<SaleDTO> saleDTOList){
        return saleDTOList.stream()
                .map(this::fromDTOtoSale)
                .collect(Collectors.toList());
    }

    public SaleDTO fromSaleToDTO(Sale sale){
        return SaleDTO.builder()
                .id(sale.getId())
                .card_number(sale.getCard_number())
                .date(sale.getDate())
                .products(productFactory.createProductDTOList(sale.getProducts())).build();
    }
    public List<SaleDTO> createListSaleDTO(List<Sale> saleList){
        return saleList.stream()
                .map(this::fromSaleToDTO)
                .collect(Collectors.toList());
    }


}
