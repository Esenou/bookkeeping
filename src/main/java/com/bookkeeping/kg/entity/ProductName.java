package com.bookkeeping.kg.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tbl_product_name")
public class ProductName extends BaseEntity{
    @Column(name = "product_name", nullable = false)
    private String name;
}
