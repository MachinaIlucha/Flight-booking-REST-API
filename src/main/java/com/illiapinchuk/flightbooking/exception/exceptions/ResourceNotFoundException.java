package com.illiapinchuk.flightbooking.exception.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceType, String keyName, Long keyValue) {
        super(resourceType + " with " + keyName + "[" + keyValue + "] not found in the database!");
    }
}
