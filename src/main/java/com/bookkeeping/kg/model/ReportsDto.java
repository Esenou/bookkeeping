package com.bookkeeping.kg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ColumnResult;



public class ReportsDto {
    public String productName;
    public String productType;
    public Double product;
    public Double brakWorkers;
    public Double brakStanok;
    public Double brakSay;
    public Double all;

    public ReportsDto(String productName, String productType, Double product, Double brakWorkers, Double brakStanok, Double brakSay, Double all) {
        this.productName = productName;
        this.productType = productType;
        this.product = product;
        this.brakWorkers = brakWorkers;
        this.brakStanok = brakStanok;
        this.brakSay = brakSay;
        this.all = all;
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

    public Double getProduct() {
        return product;
    }

    public void setProduct(Double product) {
        this.product = product;
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

    public Double getAll() {
        return all;
    }

    public void setAll(Double all) {
        this.all = all;
    }
}
