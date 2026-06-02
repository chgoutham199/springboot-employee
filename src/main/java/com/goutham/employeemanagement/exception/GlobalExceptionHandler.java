package com.goutham.employeemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(ResourceNotFoundException exception){
         Map<String,Object> response = Map.of(
                 "timestamp",LocalDateTime.now(),
                 "status" , HttpStatus.NOT_FOUND.value(),
                 "error" , "Not Found",
                 "message" , exception.getMessage()
         );

         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidationErrors(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(),error.getDefaultMessage())
        );

        Map<String,Object> response = Map.of(
                "timestamp",LocalDateTime.now(),
                "status" , HttpStatus.BAD_REQUEST.value(),
                "error" , "Bad Request",
                "message" , "Validation Failed",
                "errors" , errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

}
