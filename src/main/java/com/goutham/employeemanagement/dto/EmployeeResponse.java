package com.goutham.employeemanagement.dto;

import lombok.Getter;

@Getter
public class EmployeeResponse {

    private Long id;
    private String name;
    private String email;
    private String department;

    public EmployeeResponse(Long id , String name, String email, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
    }
}
