package com.bookkeeping.kg.controller.BaseConrolller;


import com.bookkeeping.kg.entity.BaseEntity;
import com.bookkeeping.kg.service.base.BaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public abstract class BaseController<ENTITY extends BaseEntity,  SERVICE extends BaseService<ENTITY>> {

    final private SERVICE baseService;

    protected BaseController(SERVICE baseService) {
        this.baseService = baseService;
    }


    @GetMapping("/findById/{id}")
    public ENTITY findById(@PathVariable Long id){
        return  baseService.findById(id);
    }

    @GetMapping("/findAll")
    public List<ENTITY> findAll(){
        return baseService.findByAll();
    }

    @PostMapping("/save")
    public ENTITY save(@RequestBody ENTITY model){
        return baseService.create(model);
    }

    @PutMapping("/update/{id}")
    public ENTITY update(@RequestBody ENTITY model, @PathVariable Long id){
        model.setId(id);
        return baseService.update(model);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        baseService.deleteById(id);
    }
}
