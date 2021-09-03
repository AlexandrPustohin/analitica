package com.service.analytics.repositories;

import com.service.analytics.model.Product;
import com.service.analytics.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE "+
            "s.id = :id ")
    Sale findById(long id);

    @Query("SELECT s FROM Sale s WHERE "+
            "s.card_number = :card_number ")
    List<Sale> findAllByCardNumber(long card_number);
}
