package com.bookkeeping.kg.model;

public class ProductDto {
    private String productName;
    private String productType;
    private Double packaging;
    private Double product;
    private Double inBags;
    private Double brakWorkers;
    private Double brakStanok;
    private Double brakSay;


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

    public Double getPackaging() {
        return packaging;
    }

    public void setPackaging(Double packaging) {
        this.packaging = packaging;
    }

    public Double getProduct() {
        return product;
    }

    public void setProduct(Double product) {
        this.product = product;
    }

    public Double getInBags() {
        return inBags;
    }

    public void setInBags(Double inBags) {
        this.inBags = inBags;
    }

    public Double getBrakWorkers() {
        return brakWorkers;
    }

    public void setBrakWorkers(Double brakWorkers) {
        this.brakWorkers = brakWorkers;
    }

    public Double getBrakStanok() {
        return brakStanok;
    }

    public void setBrakStanok(Double brakStanok) {
        this.brakStanok = brakStanok;
    }

    public Double getBrakSay() {
        return brakSay;
    }

    public void setBrakSay(Double brakSay) {
        this.brakSay = brakSay;
    }
}
