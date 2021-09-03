package com.service.analytics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Table(name = "sale")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sale {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long Id;
    @Column(name = "card_number")
    private long card_number;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public Sale(long id, long card_number, Date date, List<Product> products) {
        Id = id;
        this.card_number = card_number;
        this.date = date;
        this.products = products;
    }

    public Sale(long card_number, Date date, List<Product> products) {
        this.card_number = card_number;
        this.date = date;
        this.products = products;
    }

    public Sale() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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

    public List<Product> getProducts() {
        return products;
    }

   public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "Id=" + Id +
                ", card_number=" + card_number +
                ", date=" + date +
                '}';
    }
}
