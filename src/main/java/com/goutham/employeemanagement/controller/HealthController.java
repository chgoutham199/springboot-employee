package com.goutham.employeemanagement.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String landing(){
         return  "Application Running Successfully";
    }

}
