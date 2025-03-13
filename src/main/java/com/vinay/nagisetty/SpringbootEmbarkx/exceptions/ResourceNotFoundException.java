package com.vinay.nagisetty.SpringbootEmbarkx.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    String resource;
    String field;
    String resourceName;
    Long feildId;

    public ResourceNotFoundException(String resource, String field, Long feildId) {
        super(String.format("%s not found with %s: %d ",resource,field,feildId));
        this.resource = resource;
        this.field=field;
        this.feildId = feildId;
    }

    public ResourceNotFoundException(String resource, String field, String resourceName) {
        super(String.format("%s not found with %s: %s ",resource,resourceName));
        this.resource = resource;
        this.field = field;
        this.resourceName = resourceName;
    }
}
