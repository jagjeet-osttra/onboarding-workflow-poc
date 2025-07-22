package com.osttra.crds.exceptions;

public class ProcessNotFoundException extends RuntimeException{
    public ProcessNotFoundException(String message) {
        super(message);
    }
}
