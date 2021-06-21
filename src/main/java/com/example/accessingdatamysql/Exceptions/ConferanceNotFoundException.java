package com.example.accessingdatamysql.Exceptions;


public class ConferanceNotFoundException extends RuntimeException {

    public ConferanceNotFoundException(Integer id) {
        super("Cannot not find conferance " + id);
    }

}
