package com.bookkeeping.kg.service.base;

import com.bookkeeping.kg.entity.BaseEntity;
import com.bookkeeping.kg.repository.CommonRepository;

import java.util.List;


public abstract class BaseServiceImpl<Entity extends BaseEntity, Repository extends CommonRepository<Entity>> implements BaseService<Entity> {

    private final Repository commonRepository;

    public BaseServiceImpl(Repository commonRepository) {
        this.commonRepository = commonRepository;
    }

    @Override
    public Entity create(Entity dto) {
        return commonRepository.save(dto);
    }

    @Override
    public Entity update(Entity dto) {
        return commonRepository.save(dto);
    }

    @Override
    public void deleteById(Long id) {
        commonRepository.deleteById(id);
    }

    @Override
    public List<Entity> findByAll() {
        return commonRepository.findAll();
    }

    @Override
    public Entity findById(Long id) {
        return commonRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found!"));
    }
}
