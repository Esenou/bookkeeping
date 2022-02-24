package com.bookkeeping.kg.service.impl;

import com.bookkeeping.kg.model.ReportsDto;
import com.bookkeeping.kg.service.ProductService;
import com.bookkeeping.kg.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl  implements ReportService {

    private final ProductService productService;

    public ReportServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<ReportsDto> getReport() {
        return productService.getReport();
    }
}
