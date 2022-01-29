package com.bookkeeping.kg.service.impl;

import com.bookkeeping.kg.entity.Product;
import com.bookkeeping.kg.repository.ProductRepository;
import com.bookkeeping.kg.service.ProductService;
import com.bookkeeping.kg.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl <Product, ProductRepository>  implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }
}
