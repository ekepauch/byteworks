package com.xpresspayments.ZenithBank.exceptions;

public class NotFoundException extends AbstractException {

    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
