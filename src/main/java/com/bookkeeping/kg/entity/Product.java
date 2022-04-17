package com.bookkeeping.kg.entity;

import com.bookkeeping.kg.model.ReportsDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity {

    @Column(name = "count_products")
    private Double countProducts;

    @Column(name = "count_stanok")
    private Double countStanok;

    @Column(name = "count_brak")
    private Double countBrak;

    @Column(name = "count_saya")
    private Double countSaya;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product_name")
    private ProductName productName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product_type")
    private ProductType productType;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Employee> employees = new HashSet<>();

}
