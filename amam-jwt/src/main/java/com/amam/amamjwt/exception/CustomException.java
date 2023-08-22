package com.amam.amamjwt.exception;

public class CustomException extends RuntimeException {

    private Codable codable;

    public CustomException(Codable codable) {
        this.codable = codable;
    }

    public Codable getCodable() {
        return codable;
    }

}
