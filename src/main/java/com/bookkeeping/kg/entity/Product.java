package com.bookkeeping.kg.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @Column(name = "count_stanok")
    private Double countStanok;

    @Column(name = "count_brak")
    private Double countBrak;

    @Column(name = "count_saya")
    private Double countSaya;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product_name")
    @NotNull
    @Valid
    private ProductName productName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product_type")
    @NotNull
    @Valid
    private ProductType productType;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Employee> employees = new HashSet<>();

}
