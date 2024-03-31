package com.student_management.backend.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id){
        super("Could Not Found the User with Id " + id);
    }
}
