package com.teasoft.shoppingstore.business.service;

import java.util.*;

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
        List<ProductPurchase> productPurchases = new ArrayList<>();
        Iterable<Purchase> purchases = this.purchaseRepository.findPurchaseByPurchaseDate(new java.sql.Date(date.getTime()));
        purchases.forEach(purchase -> {
            ProductPurchase productPurchase = new ProductPurchase();
            Iterable<Product> products = this.productRepository.findProductById(purchase.getId());
            products.forEach(product -> {
                productPurchase.setProductId(product.getId());
                productPurchase.setProductDescription(product.getDescription());
                productPurchase.setProductPrice(product.getPrice());
                productPurchase.setDate(date);
                Customer customer = this.customerRepository.findById(purchase.getCustomerId()).get();
                productPurchase.setFirstName(customer.getFirstName());
                productPurchase.setLastName(customer.getLastName());
                productPurchase.setPurchaseId(customer.getId());
                productPurchases.add(productPurchase);
            });
        });
        Collections.sort(productPurchases);
        return productPurchases;
    }

    public List<Customer> getCustomers(){
        Iterable<Customer> customers = this.customerRepository.findAll();
        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customerList::add);
        customerList.sort((o1, o2) -> {
            if (o1.getLastName().equals(o2.getLastName())){
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
            return o1.getLastName().compareTo(o2.getLastName());
        });
        return customerList;
    }
}
