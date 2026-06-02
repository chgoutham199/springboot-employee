package com.goutham.employeemanagement.controller;

import com.goutham.employeemanagement.entity.Employee;
import com.goutham.employeemanagement.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployee(@PathVariable Long id){
        return  employeeRepository.findById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable long id,@RequestBody Employee employee){

       return employeeRepository.findById(id).map(
               emp->{
                   emp.setName(employee.getName());
                   emp.setEmail(employee.getEmail());
                   emp.setDepartment(employee.getDepartment());
                   return employeeRepository.save(emp);
               }
       ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Object>  deleteEmployee(@PathVariable Long id ){

        return employeeRepository.findById(id).map(e->{
                employeeRepository.delete(e);
                return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

    }


}
