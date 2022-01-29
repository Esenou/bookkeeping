package com.bookkeeping.kg.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tbl_modelType")
public class ProductTypeModel extends BaseEntity {
    @Column(name = "nameType", nullable = false)
    private String nameType;
}
