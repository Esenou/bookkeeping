package com.bookkeeping.kg.service;

import com.bookkeeping.kg.entity.Employee;
import com.bookkeeping.kg.entity.Product;
import com.bookkeeping.kg.entity.ProductName;
import com.bookkeeping.kg.entity.ProductType;
import com.bookkeeping.kg.model.ReportsDto;
import com.bookkeeping.kg.model.SalaryDto;
import com.bookkeeping.kg.service.base.BaseService;

import java.util.List;

public interface ProductService extends BaseService<Product> {

    List<ProductName> findByAllProductName();

    List<ProductType> findByAllProductType();

    List<Employee> findByAllEmployee();

    List<ReportsDto> getReport(String dateFrom, String dateTo);

    List<SalaryDto> getReportSalary(String dateFrom, String dateTo);
}
