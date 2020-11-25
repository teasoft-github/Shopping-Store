package com.teasoft.shoppingstore.data.repository;

import java.sql.Date;

import com.teasoft.shoppingstore.data.entity.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    Iterable<Purchase> findPurchaseByPurchaseDate(Date date);
}
