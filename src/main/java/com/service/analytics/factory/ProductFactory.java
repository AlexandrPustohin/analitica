package com.service.analytics.factory;

import com.service.analytics.dto.ProductDTO;
import com.service.analytics.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProductFactory {

    public Product fromDTOtoProduct(ProductDTO productDTO){
        return Product.builder()
                .product_code(productDTO.getProduct_code())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .count(productDTO.getCount())
                .build();
    }

    public List<Product> createProductList(List<ProductDTO> productDTOS){
        return productDTOS.stream()
                .map(this::fromDTOtoProduct)
                .collect(Collectors.toList());
    }

    public ProductDTO fromProductToDTO(Product product){
        return ProductDTO.builder()
                .product_code(product.getProduct_code())
                .name(product.getName())
                .price(product.getPrice())
                .count(product.getCount()).build();
    }

    public List<ProductDTO> createProductDTOList(List<Product> products){
        return products.stream()
                .map(this::fromProductToDTO)
                .collect(Collectors.toList());
    }


}
