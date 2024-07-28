package com.BankingAPI.exceptions;

public class PaswordInvalidException extends RuntimeException{

    public PaswordInvalidException(String message){
        super(message);
    }
}
