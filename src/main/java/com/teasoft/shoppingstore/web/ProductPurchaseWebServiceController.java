package com.teasoft.shoppingstore.web;

import java.util.Date;
import java.util.List;

import com.teasoft.shoppingstore.business.domain.ProductPurchase;
import com.teasoft.shoppingstore.business.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/purchases")
public class ProductPurchaseWebServiceController {

    private final PurchaseService purchaseService;

    @Autowired
    public ProductPurchaseWebServiceController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<ProductPurchase> getProductPurchases(@RequestParam(name="date", required = false)String dateString){
        Date date = DateUtils.createDateFromDateString(dateString);
        return this.purchaseService.getProductPurchasesForDate(date);
    }
}
