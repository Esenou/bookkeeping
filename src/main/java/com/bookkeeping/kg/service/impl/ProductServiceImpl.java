package com.bookkeeping.kg.service.impl;

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

import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl <Product, ProductRepository>  implements ProductService {

    private final ProductRepository productRepository;

    private final ProductNameService productNameService;

    private final ProductTypeService productTypeService;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductNameService productNameService,
                              ProductTypeService productTypeService) {
        super(productRepository);
        this.productRepository = productRepository;
        this.productNameService = productNameService;
        this.productTypeService = productTypeService;
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
    public List<ReportsDto> getReport() {
        return productRepository.getReport();
    }
}
