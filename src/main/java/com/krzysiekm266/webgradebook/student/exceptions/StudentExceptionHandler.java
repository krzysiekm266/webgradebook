package com.krzysiekm266.webgradebook.student.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler(value = { StudentIllegalStateException.class })
    ResponseEntity<Object> handleStudentIllegalStateException(StudentIllegalStateException e) {
        StudentIllegalStateException ex = new StudentIllegalStateException(e.getMessage());
        return new ResponseEntity<Object>(ex,HttpStatus.BAD_REQUEST);
    }
}
