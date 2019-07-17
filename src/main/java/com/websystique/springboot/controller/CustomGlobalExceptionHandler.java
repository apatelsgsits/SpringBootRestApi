package com.websystique.springboot.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.websystique.springboot.exception.CustomErrorResponse;
import com.websystique.springboot.exception.RecordNotFoundException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler  {
	 @ExceptionHandler(RecordNotFoundException.class)
	    public ResponseEntity<CustomErrorResponse> customHandleNotFound(RecordNotFoundException ex, WebRequest request) {

	        CustomErrorResponse errors = new CustomErrorResponse();
	        errors.setTimestamp(LocalDateTime.now());
	        errors.setError(ex.getMessage());
	        errors.setStatus(HttpStatus.NOT_FOUND.value());
	        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	    }
}
