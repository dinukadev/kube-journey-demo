package com.kubejourney.service.impl;

import com.kubejourney.domain.Employee;
import com.kubejourney.dto.EmployeeDto;
import com.kubejourney.dto.assemblers.EmployeeDtoAssembler;
import com.kubejourney.exceptions.EntityNotFoundException;
import com.kubejourney.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String, Employee> employeeDataCache = new ConcurrentHashMap<>();

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employeeToCreate = EmployeeDtoAssembler.disassemble(employeeDto);
        employeeDataCache.put(employeeToCreate.getId(), employeeToCreate);
        return EmployeeDtoAssembler.assemble(employeeToCreate);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employeeList =
                employeeDataCache.values().stream().map(EmployeeDtoAssembler::assemble).collect(Collectors.toList());
        return employeeList;
    }

    @Override
    public EmployeeDto getEmployeeById(String id) {
        if (!employeeDataCache.containsKey(id)) {
            throw new EntityNotFoundException("Employee not found for the passed in id");
        }
        return EmployeeDtoAssembler.assemble(employeeDataCache.get(id));
    }

    @Override
    public EmployeeDto updateEmployee(String id, EmployeeDto employeeDto) {
        if (!employeeDataCache.containsKey(id)) {
            throw new EntityNotFoundException("Employee not found for the passed in id");
        }
        Employee updatedEmployee = EmployeeDtoAssembler.disassemble(employeeDto);
        employeeDataCache.put(id, updatedEmployee);
        return EmployeeDtoAssembler.assemble(updatedEmployee);
    }
}
