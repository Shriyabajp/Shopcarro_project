package com.sreeraj.PostgresTest.exceptions;

public class EmployeeIdNotFoundException extends Exception {
    public EmployeeIdNotFoundException(String message) {
        super(message);
    }
}
