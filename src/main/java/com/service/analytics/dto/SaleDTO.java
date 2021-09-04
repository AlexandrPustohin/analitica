package com.service.analytics.dto;

import lombok.Builder;

import java.util.Date;
import java.util.List;
@Builder
public class SaleDTO {
    private long id;
    private long card_number;
    private Date date;
    private List<ProductDTO> products;

    public SaleDTO(long id,long card_number, Date date, List<ProductDTO> products) {
        this.id = id;
        this.card_number = card_number;
        this.date = date;
        this.products = products;
    }

    public SaleDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
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





    @Override
    public String toString() {
        return "Sale{" +
                "card_number=" + card_number +
                ", date=" + date +
                ", products=" + products +
                '}';
    }
}
