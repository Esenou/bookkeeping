package com.bookkeeping.kg.controller.web;

import com.bookkeeping.kg.entity.Employee;
import com.bookkeeping.kg.entity.ProductName;
import com.bookkeeping.kg.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeControllerWeb {

    private final EmployeeService employeeService;

    public EmployeeControllerWeb(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getList(Model model) {
        List<Employee> employeeList = employeeService.findByAll();
        model.addAttribute("employeeList", employeeList);
        return "employeeList";
    }

    @GetMapping("/form")
    public String getForm(Model model){
        model.addAttribute("add", true);
        model.addAttribute("employee", new Employee());
        return "employeeForm";
    }

    @GetMapping("{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        Employee employee = employeeService.findById(id);
        model.addAttribute("add",false);
        model.addAttribute("employee", employee);
        return "employeeForm";
    }

    @PostMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try {
            employeeService.deleteById(id);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("has_exception", true);
            return "redirect:/employee/" + id;
        }
        return "redirect:/employee/list";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("employee") Employee employee,
                         BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("employee",employee);
            model.addAttribute("add", true);
            return "nameForm";
        }
        employeeService.create(employee);
        return "redirect:/employee/list";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model, @ModelAttribute("employee") Employee employee, BindingResult result) {
        if(result.hasErrors()){
            model.addAttribute("add", false);
            model.addAttribute("employee",employee);
        }
        employee.setId(id);
        employeeService.update(employee);
        return "redirect:/employee/list";
    }
}
