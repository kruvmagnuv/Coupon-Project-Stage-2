package com.smrt.studentAndGrades.excpetion;

public class SchoolSystemException extends Exception {
    public SchoolSystemException() {
        super("General exception");
    }

    public SchoolSystemException(String message) {
        super(message);
    }
}
