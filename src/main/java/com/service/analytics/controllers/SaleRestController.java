package com.service.analytics.controllers;

import com.service.analytics.dto.ProductDTO;
import com.service.analytics.dto.SaleDTO;
import com.service.analytics.factory.ProductFactory;
import com.service.analytics.services.saleservice.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@CrossOrigin()
@RestController
@Transactional
@RequestMapping("/api")
public class SaleRestController {
    @Autowired
    SaleServiceImpl saleService;
    @Autowired
    ProductFactory productFactory;
    @GetMapping("/getTotalForDay/{day}")
    public ResponseEntity<Double> getTotalForDay(@PathVariable String  day) throws ParseException {
        return ResponseEntity.ok(
                saleService.getTotalForDay(day));
    }
    @GetMapping("/getBestForCard/{cardNumber}")
    public ResponseEntity<List<ProductDTO>> getBestForCard(@PathVariable String  cardNumber) throws ParseException {
        return ResponseEntity.ok(productFactory.createSimpleListProducts(saleService.getBestForCard(Long.parseLong(cardNumber))));
    }
    @GetMapping("/getAllSale")
    public ResponseEntity<List<SaleDTO>> getAllSale(){
        return ResponseEntity.ok(saleService.getAllSale());
    }
}
