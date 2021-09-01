package com.service.analytics.dto;

import lombok.Builder;

import java.util.Date;
import java.util.List;
@Builder
public class SaleDTO {
    private long id;
    private long card_number;
    private Date date;
    private List<ProductDTO> productDTOS;

    public SaleDTO(long id,long card_number, Date date, List<ProductDTO> productDTOS) {
        this.id = id;
        this.card_number = card_number;
        this.date = date;
        this.productDTOS = productDTOS;
    }

    public SaleDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProductDTO> getProductDTOS() {
        return productDTOS;
    }

    public void setProductDTOS(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }

    public long getCard_number() {
        return card_number;
    }

    public void setCard_number(long card_number) {
        this.card_number = card_number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ProductDTO> getProducts() {
        return productDTOS;
    }

    public void setProducts(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "card_number=" + card_number +
                ", date=" + date +
                ", products=" + productDTOS +
                '}';
    }
}
