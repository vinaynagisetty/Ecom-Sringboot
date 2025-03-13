package com.vinay.nagisetty.SpringbootEmbarkx.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String,String>> myGlobalException(MethodArgumentNotValidException e){
        Map<String,String> response=new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err ->{
            String fieldName= ((FieldError)err).getField();
            String message= err.getDefaultMessage();
            response.put(fieldName,message);
        });
        return   new ResponseEntity<>(response, HttpStatus.BAD_REQUEST) ;
    }


    @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException e){
   return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
   }

}
