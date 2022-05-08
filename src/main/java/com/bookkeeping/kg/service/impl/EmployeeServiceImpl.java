package com.bookkeeping.kg.service.impl;

import com.bookkeeping.kg.entity.Employee;
import com.bookkeeping.kg.repository.EmployeeRepository;
import com.bookkeeping.kg.service.EmployeeService;
import com.bookkeeping.kg.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, EmployeeRepository> implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
    }
}
