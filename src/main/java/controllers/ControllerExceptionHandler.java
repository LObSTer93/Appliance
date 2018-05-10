package controllers;

import exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody Error exceptionHandler(ApiException e){
        return new Error(e.getMessage());
    }
}