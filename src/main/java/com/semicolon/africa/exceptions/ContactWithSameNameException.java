package com.semicolon.africa.exceptions;

public class ContactWithSameNameException extends RuntimeException{
    public ContactWithSameNameException(String message){
        super(message);
    }

}
