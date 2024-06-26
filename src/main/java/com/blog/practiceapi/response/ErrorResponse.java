package com.blog.practiceapi.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@ToString
public class ErrorResponse {

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String msg;

    @JsonProperty("validation")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final ValidationError validationError;

    @Builder
    public ErrorResponse(String code, String msg, ValidationError validationError) {
        this.code = code;
        this.msg = msg;
        this.validationError = validationError;
    }


}
