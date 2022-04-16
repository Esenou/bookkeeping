package com.bookkeeping.kg.service.impl;

import com.bookkeeping.kg.dao.ReportDao;
import com.bookkeeping.kg.entity.Product;
import com.bookkeeping.kg.entity.ProductName;
import com.bookkeeping.kg.entity.ProductType;
import com.bookkeeping.kg.model.ReportsDto;
import com.bookkeeping.kg.repository.ProductRepository;
import com.bookkeeping.kg.service.ProductNameService;
import com.bookkeeping.kg.service.ProductService;
import com.bookkeeping.kg.service.ProductTypeService;
import com.bookkeeping.kg.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl <Product, ProductRepository>  implements ProductService {

    private final ProductRepository productRepository;

    private final ProductNameService productNameService;

    private final ProductTypeService productTypeService;

    private final ReportDao reportDao;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductNameService productNameService,
                              ProductTypeService productTypeService, ReportDao reportDao) {
        super(productRepository);
        this.productRepository = productRepository;
        this.productNameService = productNameService;
        this.productTypeService = productTypeService;
        this.reportDao = reportDao;
    }

    @Override
    public List<ProductName> findByAllProductName() {
        return productNameService.findByAll();
    }

    @Override
    public List<ProductType> findByAllProductType() {
        return productTypeService.findByAll();
    }

    @Override
    public List<ReportsDto> getReport(String dateFrom, String dateTo) {

        return reportDao.getReportsByBetweenDate(dateFrom,dateTo);
    }
}
