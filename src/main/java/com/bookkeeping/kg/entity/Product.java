package com.bookkeeping.kg.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity {

    @Column(name = "countProducts")
    private String countProducts;

    @Column(name = "countStanok")
    private String countStanok;

    @Column(name = "countBrak")
    private String countBrak;

    @Column(name = "countSaya")
    private String countSaya;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_productModel")
    private ProductModel productModel;


}
