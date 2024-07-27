package com.BankingAPI.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {

    private HttpStatus status;

    private String message;

    private Map<String, String> errors;

    public ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.errors = null;
    }
}
