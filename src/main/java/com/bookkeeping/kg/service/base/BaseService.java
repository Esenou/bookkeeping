package com.bookkeeping.kg.service.base;

import com.bookkeeping.kg.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    T create(T dto);

    T update(T dto);

    void deleteById(Long id);

    List<T> findByAll();

    T findById(Long id);
}
