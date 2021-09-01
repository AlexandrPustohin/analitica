package com.service.analytics.services.saleservice;

import com.service.analytics.dto.ProductDTO;
import com.service.analytics.dto.SaleDTO;
import com.service.analytics.model.Sale;

import java.text.ParseException;

import java.util.List;
import java.util.Map;

public interface SaleService {
    void saveSale(Sale sale);
    void saveAll(List<SaleDTO> saleDTOS);

     List<SaleDTO> getAllSale ();

    double getTotalForDay(String day) throws ParseException ;

    double getSummCard(SaleDTO saleDTO);

    List<ProductDTO> getProductsByIdSale(long id);

    Map<String, Double> getBestForCard(long card_number);
}
