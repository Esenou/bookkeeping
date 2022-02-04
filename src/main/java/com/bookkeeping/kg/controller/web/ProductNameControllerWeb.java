package com.bookkeeping.kg.controller.web;

import com.bookkeeping.kg.entity.Product;
import com.bookkeeping.kg.entity.ProductName;
import com.bookkeeping.kg.entity.ProductType;
import com.bookkeeping.kg.service.ProductNameService;
import com.bookkeeping.kg.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/name")
public class ProductNameControllerWeb {

    private final ProductNameService productNameService;

    public ProductNameControllerWeb(ProductNameService productNameService) {
        this.productNameService = productNameService;
    }

    @GetMapping("/list")
    public String getList(Model model) {
        List<ProductName> nameList = productNameService.findByAll();
        model.addAttribute("nameList", nameList);
        return "nameList";
    }

    @GetMapping("/form")
    public String getForm(Model model){
        model.addAttribute("add", true);
        model.addAttribute("productName", new ProductName());
        return "nameForm";
    }

    @GetMapping("{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        ProductName productName = productNameService.findById(id);
        model.addAttribute("add",false);
        model.addAttribute("productName", productName);
        return "nameForm";
    }

    @PostMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try {
            productNameService.deleteById(id);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("has_exception", true);
            return "redirect:/name/" + id;
        }
        return "redirect:/name/list";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("productName") ProductName productName,
                         BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("productName",productName);
            model.addAttribute("add", true);
            return "nameForm";
        }
        productNameService.create(productName);
        return "redirect:/name/list";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model, @ModelAttribute("productName") ProductName productName, BindingResult result) {
        if(result.hasErrors()){
            model.addAttribute("add", false);
            model.addAttribute("productName",productName);
        }
        productName.setId(id);
        productNameService.update(productName);
        return "redirect:/name/list";
    }
}
