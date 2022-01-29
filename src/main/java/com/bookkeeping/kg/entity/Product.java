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

    @ManyToOne(fetch = FetchType.EAGER ,cascade=CascadeType.ALL)
    @JoinColumn(name = "id_product_model")
    private ProductModel productModel;


}
