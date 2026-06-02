package com.goutham.employeemanagement.service;

import com.goutham.employeemanagement.entity.Employee;
import com.goutham.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id){
        return  employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id,Employee employee){

         Employee existingEmployee =  getEmployee(id);
         existingEmployee.setName(employee.getName());
         existingEmployee.setEmail(employee.getEmail());
         existingEmployee.setDepartment(employee.getDepartment());
         return employeeRepository.save(existingEmployee);

    }


    public void  deleteEmployee(Long id ){
        Employee existingEmployee =  getEmployee(id);
        employeeRepository.delete(existingEmployee);
    }



}
