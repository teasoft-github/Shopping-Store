package com.teasoft.shoppingstore.business.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teasoft.shoppingstore.business.domain.ProductPurchase;
import com.teasoft.shoppingstore.data.entity.Customer;
import com.teasoft.shoppingstore.data.entity.Purchase;
import com.teasoft.shoppingstore.data.entity.Product;
import com.teasoft.shoppingstore.data.repository.CustomerRepository;
import com.teasoft.shoppingstore.data.repository.PurchaseRepository;
import com.teasoft.shoppingstore.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(ProductRepository productRepository, CustomerRepository customerRepository, PurchaseRepository purchaseRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public List<ProductPurchase> getProductPurchasesForDate(Date date){
        Iterable<Product> products = this.productRepository.findAll();
        Map<Long, ProductPurchase> productPurchaseMap = new HashMap();
        products.forEach(product -> {
            ProductPurchase productPurchase = new ProductPurchase();
            productPurchase.setProductId(product.getId());
            productPurchase.setProductDescription(product.getDescription());
            productPurchase.setProductPrice(product.getPrice());
            productPurchaseMap.put(product.getId(), productPurchase);
        });
        Iterable<Purchase> purchases = this.purchaseRepository.findPurchaseByDate(new java.sql.Date(date.getTime()));
        purchases.forEach(purchase -> {
            ProductPurchase productPurchase = productPurchaseMap.get(purchase.getProductId());
            productPurchase.setDate(date);
            Customer customer = this.customerRepository.findById(purchase.getCustomerId()).get();
            productPurchase.setFirstName(customer.getFirstName());
            productPurchase.setLastName(customer.getLastName());
            productPurchase.setPurchaseId(customer.getId());
        });
        List<ProductPurchase> productPurchases = new ArrayList<>();
        for(Long id: productPurchaseMap.keySet()){
            productPurchases.add(productPurchaseMap.get(id));
        }
        productPurchases.sort((o1, o2) -> {
            if (o1.getProductDescription() == o2.getProductDescription()){
                return Long.valueOf(o1.getProductPrice()).compareTo(Long.valueOf(o2.getProductPrice()));
            }
            return o1.getProductDescription().compareTo(o2.getProductDescription());
        });
        return productPurchases;
    }

    public List<Customer> getCustomers(){
        Iterable<Customer> customers = this.customerRepository.findAll();
        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customer -> customerList.add(customer));
        customerList.sort((o1, o2) -> {
            if (o1.getLastName() == o2.getLastName()){
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
            return o1.getLastName().compareTo(o2.getLastName());
        });
        return customerList;
    }
}
