package com.shopSphere.image_service.Util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    @JsonProperty("Response")
    private Object response;
    @JsonProperty("Message")
    private String message;

    @JsonProperty("success")
    private boolean success;
    public ApiResponse( String message,boolean success) {
        this.message = message;
        this.success = success;
    }

    public ApiResponse(Object response, String message, boolean success) {
        this.response = response;
        this.message = message;
        this.success = success;
    }
}

