package com.goutham.employeemanagement.dto;

import lombok.Getter;

@Getter
public class EmployeeResponse {

    private final Long id;
    private final String name;
    private final String email;
    private final String department;

    public EmployeeResponse(Long id , String name, String email, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
    }
}
