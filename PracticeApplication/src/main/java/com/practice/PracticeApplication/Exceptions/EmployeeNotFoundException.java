package com.practice.PracticeApplication.Exceptions;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException{
   public EmployeeNotFoundException(String process, UUID id){
        super(process + " Employee with ID : " +
                id + "\nFailed : " +
                "Employee is not available for the given ID.");
    }

}
