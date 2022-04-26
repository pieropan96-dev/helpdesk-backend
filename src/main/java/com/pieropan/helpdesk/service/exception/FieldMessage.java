package com.pieropan.helpdesk.service.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {
    private String message;
    private String field;

    public FieldMessage(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public FieldMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}