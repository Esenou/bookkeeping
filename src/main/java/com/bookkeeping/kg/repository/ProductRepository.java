package com.bookkeeping.kg.repository;

import com.bookkeeping.kg.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends CommonRepository<Product> {

    @Query("SELECT m FROM Product m WHERE m.productName.name LIKE %:text% or m.productType.type LIKE %:text% order by m.createDateProduct desc")
    Page<Product> findByProductNameOrProductType(Pageable pageable, String text);

    @Query("SELECT m FROM Product m order by m.createDateProduct desc")
    Page<Product> findAllByProductNameOrProductType(Pageable pageable);
}
