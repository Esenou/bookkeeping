package com.bookkeeping.kg.controller.web;

import com.bookkeeping.kg.entity.Employee;
import com.bookkeeping.kg.entity.Product;
import com.bookkeeping.kg.entity.ProductType;
import com.bookkeeping.kg.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductControllerWeb {

    private final ProductService productService;

    public ProductControllerWeb(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String getList(@RequestParam(value = "search", required = false) String search,
                          @PageableDefault(7) Pageable pageable,
                          Model model) {
        Page<Product> productsList = null;
        if(search != null && !search.isEmpty()){
            productsList = productService.findByProductNameOrProductType(pageable,search);
        } else {
            productsList = productService.findByAllWithPagination(pageable);
        }

        model.addAttribute("productsList", productsList);
        return "productsList";
    }

    @GetMapping("/form")
    public String getForm(Model model){
        List<Product> productsList = productService.findByAll();
        model.addAttribute("add", true);
        model.addAttribute("product", new Product());
        model.addAttribute("productNameList",  productService.findByAllProductName());
        model.addAttribute("productTypeList",  productService.findByAllProductType());
        model.addAttribute("employeeList",  productService.findByAllEmployee());
        model.addAttribute("employee",  new Employee());
        return "productForm";
    }

    @GetMapping("{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("add",false);
        model.addAttribute("product",product);
        model.addAttribute("productNameList",  productService.findByAllProductName());
        model.addAttribute("productTypeList",  productService.findByAllProductType());
        model.addAttribute("employeeList",  productService.findByAllEmployee());
        return "productForm";
    }

    @PostMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try {
            productService.deleteById(id);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("has_exception", true);
            return "redirect:/product/" + id;
        }
        return "redirect:/product/list";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("product") Product product,
                         BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("product",product);
            model.addAttribute("add", true);
            model.addAttribute("productNameList",  productService.findByAllProductName());
            model.addAttribute("productTypeList",  productService.findByAllProductType());
            model.addAttribute("employeeList",  productService.findByAllEmployee());
            return "productForm";
        }
        productService.create(product);
        return "redirect:/product/list";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model, @ModelAttribute("product") Product product, BindingResult result) {
        if(result.hasErrors()){
            model.addAttribute("add", false);
            model.addAttribute("product",product);
        }
        product.setId(id);
        productService.update(product);
        return "redirect:/product/list";
    }


    @GetMapping("/getTypeModelList")
    @ResponseBody
    public List<ProductType> productTypeModelList(@RequestParam Long id, Model model){
        return (List<ProductType>) productService.findById(id);
    }
}
