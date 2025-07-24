package com.practice.PracticeApplication.Exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String process, long id){
        super(process + " Employee with ID : " +
                id + "\nFailed : " +
                "Employee is not available for the given ID.");
    }

}
