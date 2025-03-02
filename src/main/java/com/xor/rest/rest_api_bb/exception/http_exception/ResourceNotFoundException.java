package com.xor.rest.rest_api_bb.exception.http_exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;
    private final String resourceValue;
    private final String resourceColumn;
    public ResourceNotFoundException(
            final String resourceName,
            final String resourceValue,
            final String resourceColumn
    ) {
        super(String.format("Resource %s; column %s; with value %s; is not found", resourceName, resourceColumn, resourceValue));
        this.resourceName = resourceName;
        this.resourceColumn = resourceColumn;
        this.resourceValue = resourceValue;
    }
}
