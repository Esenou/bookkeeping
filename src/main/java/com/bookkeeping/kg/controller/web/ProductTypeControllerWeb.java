package com.bookkeeping.kg.controller.web;

import com.bookkeeping.kg.entity.ProductType;
import com.bookkeeping.kg.service.ProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/type")
public class ProductTypeControllerWeb {

    private final ProductTypeService productTypeService;

    public ProductTypeControllerWeb(ProductTypeService producttypeService) {
        this.productTypeService = producttypeService;
    }

    @GetMapping("/list")
    public String getList(Model model) {
        List<ProductType> typeList = productTypeService.findByAll();
        model.addAttribute("typeList", typeList);
        return "typeList";
    }

    @GetMapping("/form")
    public String getForm(Model model){
        model.addAttribute("add", true);
        model.addAttribute("productType", new ProductType());
        return "typeForm";
    }

    @GetMapping("{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        ProductType productType = productTypeService.findById(id);
        model.addAttribute("add",false);
        model.addAttribute("productType", productType);
        return "typeForm";
    }

    @PostMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try {
            productTypeService.deleteById(id);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("has_exception", true);
            return "redirect:/type/" + id;
        }
        return "redirect:/type/list";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("productType") ProductType productType,
                         BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("productType",productType);
            model.addAttribute("add", true);
            return "typeForm";
        }
        productTypeService.create(productType);
        return "redirect:/type/list";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model,
                         @ModelAttribute("productType") ProductType productType,
                         BindingResult result) {
        if(result.hasErrors()){
            model.addAttribute("add", false);
            model.addAttribute("productType",productType);
        }
        productType.setId(id);
        productTypeService.update(productType);
        return "redirect:/type/list";
    }
}
