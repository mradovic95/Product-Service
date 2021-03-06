package com.webshop.products.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {

    public NotFoundException(String errorDescription) {
        super(errorDescription, HttpStatus.NOT_FOUND);
    }

}
