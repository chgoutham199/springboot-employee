package com.goutham.employeemanagement.controller;

import com.goutham.employeemanagement.dto.EmployeeRequest;
import com.goutham.employeemanagement.dto.EmployeeResponse;
import com.goutham.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable Long id){
        return  employeeService.getEmployee(id);
    }

    @PostMapping
    public EmployeeResponse createEmployee(@Valid  @RequestBody EmployeeRequest employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeRequest employee){
        return employeeService.updateEmployee(id,employee);
    }

    @DeleteMapping("/{id}")
    public  void  deleteEmployee(@PathVariable Long id ){
        employeeService.deleteEmployee(id);
    }

}
