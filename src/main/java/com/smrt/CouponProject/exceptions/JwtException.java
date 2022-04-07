package com.smrt.CouponProject.exceptions;

public class JwtException extends Exception{

    public JwtException() {
        super("General Error");
    }

    public JwtException(String message) {
        super(message);
    }
}
