package com.smrt.CouponProject.advice;

import com.smrt.CouponProject.beans.ErrorDetails;
import com.smrt.CouponProject.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CouponRestException {
    @ExceptionHandler(value = {LoginException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDetails handleException(Exception err) {
        return new ErrorDetails("Login Error", err.getMessage());
    }

    @ExceptionHandler(value = {JwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDetails handleJWTException(Exception err) {
        return new ErrorDetails("Token Error", err.getMessage());
    }

    @ExceptionHandler(value = {PurchaseException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDetails handleCustomerException(Exception err) {
        return new ErrorDetails("Purchase Error", err.getMessage());
    }

    @ExceptionHandler(value = {CompanyException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDetails handleCompanyException(Exception err) {
        return new ErrorDetails("Company Error", err.getMessage());
    }

    @ExceptionHandler(value = {AdministrationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDetails handleAdminException(Exception err) {
        return new ErrorDetails("Administrator Error", err.getMessage());
    }
}
