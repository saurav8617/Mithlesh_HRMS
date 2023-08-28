package com.hrms.exceptions;

import java.util.Date;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

 

@ControllerAdvice
public class CustomExceptionHandler {

 

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NotFoundException nfe) {
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(nfe.getMessage());
        employeeErrorResponse.setTimeStamp(new Date());
//        employeeErrorResponse.setTimeStamp(System.currentTimeMillis());

 

        return new ResponseEntity<ErrorResponse>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

 

    @ExceptionHandler(value = InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleException(InvalidDataException ide) {
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(ide.getMessage());
        employeeErrorResponse.setTimeStamp(new Date());
//        employeeErrorResponse.setTimeStamp(System.currentTimeMillis());

 

        return new ResponseEntity<ErrorResponse>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

 

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException dive) {
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(dive.getMessage());
        employeeErrorResponse.setTimeStamp(new Date());

 

        return new ResponseEntity<ErrorResponse>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

 

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException hmnre) {
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage("Validation Failed: Inputs in Wrong datatype");
        employeeErrorResponse.setTimeStamp(new Date());

 

        return new ResponseEntity<ErrorResponse>(employeeErrorResponse, HttpStatus.NOT_FOUND);
    }

 

}