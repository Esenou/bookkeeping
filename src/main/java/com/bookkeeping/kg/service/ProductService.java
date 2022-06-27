package com.bookkeeping.kg.service;

import com.bookkeeping.kg.entity.Employee;
import com.bookkeeping.kg.entity.Product;
import com.bookkeeping.kg.entity.ProductName;
import com.bookkeeping.kg.entity.ProductType;
import com.bookkeeping.kg.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    Page<Product> findByProductNameOrProductTypeAndOrderByCreateDateProductDesc(Pageable pageable, String text);

    List<ProductName> findByAllProductName();

    List<ProductType> findByAllProductType();

    List<Employee> findByAllEmployee();

    Page<Product> findAllByProductNameOrProductType(Pageable pageable);
}
