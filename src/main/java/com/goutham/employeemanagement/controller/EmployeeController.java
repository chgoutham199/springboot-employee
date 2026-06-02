package com.goutham.employeemanagement.controller;

import com.goutham.employeemanagement.dto.EmployeeRequest;
import com.goutham.employeemanagement.dto.EmployeeResponse;
import com.goutham.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid  @RequestBody EmployeeRequest employee){
        EmployeeResponse response = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeRequest employee){
        return employeeService.updateEmployee(id,employee);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void>  deleteEmployee(@PathVariable Long id ){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
