package com.teasoft.shoppingstore.data.repository;

import com.teasoft.shoppingstore.data.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
