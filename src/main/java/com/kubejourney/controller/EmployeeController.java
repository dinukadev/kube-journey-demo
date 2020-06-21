package com.kubejourney.controller;

import com.kubejourney.constants.KubeJourneyConstant;
import com.kubejourney.dto.EmployeeDto;
import com.kubejourney.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = KubeJourneyConstant.APIConstant.API_EMPLOYEE)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return createdEmployee;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getEmployee() {
        List<EmployeeDto> employeeList = employeeService.getAllEmployees();
        return employeeList;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getEmployee(@PathVariable("id") String id) {
        EmployeeDto employee = employeeService.getEmployeeById(id);
        return employee;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto employee = employeeService.updateEmployee(id,employeeDto);
        return employee;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable("id") String id) {
       employeeService.deleteEmployee(id);
    }

}
