package com.bookkeeping.kg.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_product_type")
public class ProductType extends BaseEntity {
    @Column(name = "product_type", nullable = false)
    private String type;
}
