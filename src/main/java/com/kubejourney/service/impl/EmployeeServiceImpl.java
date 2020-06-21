package com.kubejourney.service.impl;

import com.kubejourney.domain.Employee;
import com.kubejourney.dto.EmployeeDto;
import com.kubejourney.dto.assemblers.EmployeeDtoAssembler;
import com.kubejourney.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String,Employee>employeeDataCache = new ConcurrentHashMap<>();

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employeeToCreate = EmployeeDtoAssembler.disassemble(employeeDto);
        employeeDataCache.put(employeeToCreate.getId(),employeeToCreate);
        return EmployeeDtoAssembler.assemble(employeeToCreate);
    }
}
