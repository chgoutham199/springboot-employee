package com.goutham.employeemanagement.service;

import com.goutham.employeemanagement.dto.EmployeeRequest;
import com.goutham.employeemanagement.dto.EmployeeResponse;
import com.goutham.employeemanagement.entity.Employee;
import com.goutham.employeemanagement.exception.ResourceNotFoundException;
import com.goutham.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeResponse> getAllEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public EmployeeResponse getEmployee(Long id){
        Employee employee =  employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        return  convertToResponse(employee);
    }

    public EmployeeResponse createEmployee(EmployeeRequest employee){
        Employee emp = new Employee();
        emp.setName(employee.getName());
        emp.setDepartment(employee.getDepartment());
        emp.setEmail(employee.getEmail());

        return convertToResponse(employeeRepository.save(emp));
    }

    public EmployeeResponse updateEmployee(Long id,EmployeeRequest employee){

         Employee existingEmployee = employeeRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
         existingEmployee.setName(employee.getName());
         existingEmployee.setEmail(employee.getEmail());
         existingEmployee.setDepartment(employee.getDepartment());
         return convertToResponse(employeeRepository.save(existingEmployee));

    }


    public void  deleteEmployee(Long id ){
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(existingEmployee);
    }

    public EmployeeResponse convertToResponse(Employee employee){
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment()
        );
    }

}
