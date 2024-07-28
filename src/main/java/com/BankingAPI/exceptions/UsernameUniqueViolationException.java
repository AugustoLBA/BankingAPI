package com.BankingAPI.exceptions;

public class UsernameUniqueViolationException extends RuntimeException{

    public UsernameUniqueViolationException(String message){
        super(message);
    }
}
