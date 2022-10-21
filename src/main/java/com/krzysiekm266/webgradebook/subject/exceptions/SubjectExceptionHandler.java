package com.krzysiekm266.webgradebook.subject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SubjectExceptionHandler {
    
    @ExceptionHandler(value = {SubjectIllegalStateException.class})
    public ResponseEntity<Object> handleSubjectIllegalStateException(SubjectIllegalStateException e) {
        SubjectIllegalStateException ex = new SubjectIllegalStateException(e.getMessage());
        return new ResponseEntity<Object>(ex,HttpStatus.BAD_REQUEST);

    }
}
