package com.bookkeeping.kg.service.impl;

import com.bookkeeping.kg.entity.ProductType;
import com.bookkeeping.kg.repository.ProductTypeRepository;
import com.bookkeeping.kg.service.ProductTypeService;
import com.bookkeeping.kg.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, ProductTypeRepository> implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
        super(productTypeRepository);
        this.productTypeRepository = productTypeRepository;
    }
}
