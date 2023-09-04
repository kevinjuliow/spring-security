package com.projects.ExceptionMessage;

public class UsernameExist extends RuntimeException{
    public UsernameExist(String message){
        super(message);
    }
}
