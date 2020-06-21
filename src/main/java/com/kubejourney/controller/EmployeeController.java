package com.kubejourney.controller;

import com.kubejourney.constants.KubeJourneyConstant;
import com.kubejourney.dto.EmployeeDto;
import com.kubejourney.service.EmployeeService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public EmployeeDto getEmployee(){
        return new EmployeeDto("sss","dd", DateTime.now(),"ddd");
    }

}
