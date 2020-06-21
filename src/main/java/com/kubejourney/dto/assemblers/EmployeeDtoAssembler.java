package com.kubejourney.dto.assemblers;

import com.kubejourney.domain.Employee;
import com.kubejourney.dto.EmployeeDto;

import java.util.UUID;

public class EmployeeDtoAssembler {

    public static Employee disassemble(EmployeeDto employeeDto) {
        return new Employee(UUID.randomUUID().toString(),
                employeeDto.getName(), employeeDto.getDob(),
                employeeDto.getDepartment());
    }

    public static EmployeeDto assemble(Employee employee) {
        return new EmployeeDto(employee.getId(),
                employee.getName(), employee.getDob(), employee.getDepartment());
    }
}
