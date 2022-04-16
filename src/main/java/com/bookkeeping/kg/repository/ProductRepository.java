package com.bookkeeping.kg.repository;

import com.bookkeeping.kg.entity.Product;
import com.bookkeeping.kg.model.ReportsDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends CommonRepository<Product> {

    @Query(nativeQuery = true, name = "getReport")
    List<ReportsDto> getReport(LocalDateTime startDate, LocalDateTime endDate);
}
