package com.bookkeeping.kg.service.impl;

import com.bookkeeping.kg.entity.ProductName;
import com.bookkeeping.kg.repository.ProductNameRepository;
import com.bookkeeping.kg.service.ProductNameService;
import com.bookkeeping.kg.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductNameServiceImpl extends BaseServiceImpl<ProductName, ProductNameRepository> implements ProductNameService {

    private ProductNameRepository productNameRepository;

    public ProductNameServiceImpl(ProductNameRepository productNameRepository) {
        super(productNameRepository);
        this.productNameRepository = productNameRepository;
    }


}
