package com.example.accessingdatamysql.Exceptions;

public class NameNotFoundException extends RuntimeException {

    public NameNotFoundException(String name) {
        super("Cannot not find user " + name);
    }


}
