package com.example.accessingdatamysql.Exceptions;

public class AlreadyExistException extends RuntimeException{

    public AlreadyExistException(String name) {
        super("Podany login jest już zajęty");
    }
}
