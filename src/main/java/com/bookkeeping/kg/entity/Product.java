package com.bookkeeping.kg.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity {

    @Column(name = "count_products")
    private String countProducts;

    @Column(name = "count_stanok")
    private String countStanok;

    @Column(name = "count_brak")
    private String countBrak;

    @Column(name = "count_saya")
    private String countSaya;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product_name")
    private ProductName productName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product_type")
    private ProductType productType;


}
