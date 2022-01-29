package com.bookkeeping.kg.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_model")
public class ProductModel extends BaseEntity {

    @Column(name = "nameModel", nullable = false)
    private String nameModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_productTypeModel")
    private ProductTypeModel productTypeModel;

}
