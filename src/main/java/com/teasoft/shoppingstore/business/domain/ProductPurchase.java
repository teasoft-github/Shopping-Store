package com.teasoft.shoppingstore.business.domain;

import java.util.Date;

public class ProductPurchase {
    private long productId;
    private long purchaseId;
    private String productDescription;
    private long productPrice;
    private String firstName;
    private String lastName;
    private Date date;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
