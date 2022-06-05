package com.bookkeeping.kg.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity {

    @Column(name = "count_products")
    @NotNull
    private Double countProducts;

    @Column(name = "packaging")
    @NotNull
    private Double packaging;

    @Column(name = "in_bags")
    @NotNull
    private Double inBags;

    @Column(name = "count_stanok")
    private Double countStanok;

    @Column(name = "count_brak")
    private Double countBrak;

    @Column(name = "count_saya")
    private Double countSaya;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product_name")
    @NotNull
    private ProductName productName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product_type")
    @NotNull
    private ProductType productType;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Employee> employees = new HashSet<>();

    @Column(updatable = true, nullable = false,name = "create_date_product")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createDateProduct;

}
