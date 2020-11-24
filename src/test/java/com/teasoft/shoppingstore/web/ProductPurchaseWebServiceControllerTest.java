package com.teasoft.shoppingstore.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.teasoft.shoppingstore.business.domain.ProductPurchase;
import com.teasoft.shoppingstore.business.service.PurchaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ProductPurchaseWebController.class)
public class ProductPurchaseWebServiceControllerTest {
    @MockBean
    private PurchaseService purchaseService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPurchases() throws Exception{
        String dateString = "2020-01-01";
        Date date = DateUtils.createDateFromDateString(dateString);
        List<ProductPurchase> productPurchases = new ArrayList<>();
        ProductPurchase productPurchase = new ProductPurchase();
        productPurchase.setLastName("Unit");
        productPurchase.setFirstName("Junit");
        productPurchase.setDate(date);
        productPurchase.setPurchaseId(1);
        productPurchase.setProductId(100);
        productPurchase.setProductDescription("Junit Product");
        productPurchase.setProductPrice(100);
        productPurchases.add(productPurchase);
        given(purchaseService.getProductPurchasesForDate(date)).willReturn(productPurchases);

        this.mockMvc.perform(get("/api/purchases?date=2020-01-01"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Unit, Junit")));
    }
}
