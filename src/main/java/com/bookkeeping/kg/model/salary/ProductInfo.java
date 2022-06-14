package com.bookkeeping.kg.model.salary;

import java.sql.Date;

public class ProductInfo {
    private Long id;
    private String productName;
    private String productType;
    private float packaging;
    private float product;
    private float inBags;
    private float brakWorkers;
    private float brakStanok;
    private float brakSay;
    private float madeProductCurrency;
    private float madeBrakCurrency;
    private float madeWorkersCurrency;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public float getPackaging() {
        return packaging;
    }

    public void setPackaging(float packaging) {
        this.packaging = packaging;
    }

    public float getProduct() {
        return product;
    }

    public void setProduct(float product) {
        this.product = product;
    }

    public float getInBags() {
        return inBags;
    }

    public void setInBags(float inBags) {
        this.inBags = inBags;
    }

    public float getBrakWorkers() {
        return brakWorkers;
    }

    public void setBrakWorkers(float brakWorkers) {
        this.brakWorkers = brakWorkers;
    }

    public float getBrakStanok() {
        return brakStanok;
    }

    public void setBrakStanok(float brakStanok) {
        this.brakStanok = brakStanok;
    }

    public float getBrakSay() {
        return brakSay;
    }

    public void setBrakSay(float brakSay) {
        this.brakSay = brakSay;
    }

    public float getMadeProductCurrency() {
        return madeProductCurrency;
    }

    public void setMadeProductCurrency(float madeProductCurrency) {
        this.madeProductCurrency = madeProductCurrency;
    }

    public float getMadeBrakCurrency() {
        return madeBrakCurrency;
    }

    public void setMadeBrakCurrency(float madeBrakCurrency) {
        this.madeBrakCurrency = madeBrakCurrency;
    }

    public float getMadeWorkersCurrency() {
        return madeWorkersCurrency;
    }

    public void setMadeWorkersCurrency(float madeWorkersCurrency) {
        this.madeWorkersCurrency = madeWorkersCurrency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
