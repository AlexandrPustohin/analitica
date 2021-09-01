package com.service.analytics.model;

import lombok.Builder;

import javax.persistence.*;
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(name = "product_code")
    private long product_code;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "count")
    private double count;
    @ManyToOne
    private Sale sale;

    public Product(long id, long product_code, String name, double price, double count, Sale sale) {
        this.id = id;
        this.product_code = product_code;
        this.name = name;
        this.price = price;
        this.count = count;
        this.sale = sale;
    }

    public Product(long product_code, String name, double price, double count, Sale sale) {
        this.product_code = product_code;
        this.name = name;
        this.price = price;
        this.count = count;
        this.sale = sale;
    }

    public Product() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
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
