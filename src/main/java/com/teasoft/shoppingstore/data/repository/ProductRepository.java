package com.teasoft.shoppingstore.data.repository;

import com.teasoft.shoppingstore.data.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
