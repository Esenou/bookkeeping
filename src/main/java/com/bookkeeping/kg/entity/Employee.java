package com.bookkeeping.kg.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tbl_employee")
public class Employee  extends BaseEntity {
    @Column(name = "surname")
    String surname;

    @Column(name = "name")
    String name;

    @Column(name = "phone")
    String phone;

    @Column(name = "salary")
    Double salary;

    @ManyToMany(mappedBy = "employees")
    Set<Product> projects = new HashSet<>();
}
