package com.pieropan.helpdesk.service.exception;

public class Dataintegrityviolationexception extends RuntimeException {
    public Dataintegrityviolationexception(String message) {
        super(message);
    }

    public Dataintegrityviolationexception(String message, Throwable cause) {
        super(message, cause);
    }
}