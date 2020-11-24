package com.teasoft.shoppingstore.web;

import com.teasoft.shoppingstore.business.service.PurchaseService;
import com.teasoft.shoppingstore.data.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerWebController {

    private final PurchaseService purchaseService;

    @Autowired
    public CustomerWebController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public String getCustomer(Model model){
        List<Customer> customers = this.purchaseService.getCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }
}