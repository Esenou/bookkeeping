package com.bookkeeping.kg.controller.common;

import com.bookkeeping.kg.controller.BaseConrolller.BaseController;
import com.bookkeeping.kg.entity.Product;
import com.bookkeeping.kg.model.ReportsDto;
import com.bookkeeping.kg.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public class ProductController extends BaseController<Product, ProductService> {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
    }

    @GetMapping("/getReport")
    ResponseEntity<ReportsDto> getReport(){
        return new ResponseEntity(productService.getReport(), HttpStatus.OK);
    }
}
