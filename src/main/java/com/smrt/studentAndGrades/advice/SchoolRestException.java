package com.smrt.studentAndGrades.advice;

import com.smrt.studentAndGrades.excpetion.SchoolSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class SchoolRestException {
    @ExceptionHandler(value = {SchoolSystemException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetails handleException(Exception e) {
        return new ErrorDetails("School Error", e.getMessage());
    }
}
