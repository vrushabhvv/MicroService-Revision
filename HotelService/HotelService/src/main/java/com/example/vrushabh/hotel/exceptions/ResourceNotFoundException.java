package com.example.vrushabh.hotel.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("resource is not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
