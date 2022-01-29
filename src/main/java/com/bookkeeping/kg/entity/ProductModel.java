package com.bookkeeping.kg.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_model")
public class ProductModel extends BaseEntity {

    @Column(name = "name_model", nullable = false)
    private String nameModel;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "id_product_type_model")
    private ProductTypeModel productTypeModel;

}
