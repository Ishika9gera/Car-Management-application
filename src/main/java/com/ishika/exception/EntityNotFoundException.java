package com.ishika.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Could not find entity with id.")
public class EntityNotFoundException extends Exception {
    private static final long serialVersionUID = 7022245482855934152L;


    public EntityNotFoundException(String message) {
        super(message);
    }

}
