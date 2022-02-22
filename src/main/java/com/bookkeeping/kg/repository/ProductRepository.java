package com.bookkeeping.kg.repository;

import com.bookkeeping.kg.entity.Product;
import com.bookkeeping.kg.model.ReportsDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends CommonRepository<Product> {

    @Query(nativeQuery = true, name = "getReport")
    List<ReportsDto> getReport();
}
