package com.service.analytics.dto;

import lombok.Builder;

@Builder
public class ProductDTO {
    private long product_code;
    private String name;
    private double price;
    private double count;

    public ProductDTO(long product_code, String name, double price, double count) {
        this.product_code = product_code;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public ProductDTO() {
    }

    public ProductDTO(String name,  double count) {
        this.name = name;

        this.count = count;
    }

    public long getProduct_code() {
        return product_code;
    }

    public void setProduct_code(long product_code) {
        this.product_code = product_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_code=" + product_code +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
