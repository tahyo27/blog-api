package com.blog.practiceapi.controller;

import com.blog.practiceapi.error.ErrorResponse;
import com.blog.practiceapi.error.ValidationError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String exceptionHandler(MethodArgumentNotValidException e) {

        Map<String, String> fieldErrors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(objectError -> {
           String fieldName = ((FieldError) objectError).getField();
           String msg = ((FieldError) objectError).getDefaultMessage();
           fieldErrors.put(fieldName, msg);
        });

        ValidationError validationError = new ValidationError(fieldErrors);
        ErrorResponse errorResponse = new ErrorResponse("400", "검증 오류", validationError);
        String jsonString = null;

        try {
            jsonString = new ObjectMapper().writeValueAsString(errorResponse);
        } catch (JsonProcessingException ex) {
            log.error("Json Processing Error");
        }

        return jsonString;
    }
}
