package com.bookkeeping.kg.repository;

import com.bookkeeping.kg.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonRepository<E extends BaseEntity> extends JpaRepository<E, Long> {
}
