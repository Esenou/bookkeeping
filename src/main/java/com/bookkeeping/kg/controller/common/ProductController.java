package com.bookkeeping.kg.controller.common;

import com.bookkeeping.kg.controller.BaseConrolller.BaseController;
import com.bookkeeping.kg.entity.Product;
import com.bookkeeping.kg.service.ProductService;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/product")
public class ProductController extends BaseController<Product, ProductService> {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
    }
}
