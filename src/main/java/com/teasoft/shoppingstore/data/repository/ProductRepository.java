package com.teasoft.shoppingstore.data.repository;

import com.teasoft.shoppingstore.data.entity.Product;
import com.teasoft.shoppingstore.data.entity.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findProductById(long id);
}
