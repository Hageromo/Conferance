package com.example.accessingdatamysql.Exceptions;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer id) {
        super("Cannot not find user " + id);
    }

}