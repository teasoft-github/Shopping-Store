package com.teasoft.shoppingstore.web;

import java.util.Date;
import java.util.List;

import com.teasoft.shoppingstore.business.domain.ProductPurchase;
import com.teasoft.shoppingstore.business.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/purchases")
public class ProductPurchaseWebController {
    private final PurchaseService purchaseService;

    @Autowired
    public ProductPurchaseWebController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public String getPurchases(@RequestParam(value="date", required = false)String dateString, Model model){
        Date date = DateUtils.createDateFromDateString(dateString);
        List<ProductPurchase> productPurchases = this.purchaseService.getProductPurchasesForDate(date);
        model.addAttribute("productPurchases", productPurchases);
        return "purchases";
    }
}
