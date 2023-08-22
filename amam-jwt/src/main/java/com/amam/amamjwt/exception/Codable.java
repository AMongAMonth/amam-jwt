package com.amam.amamjwt.exception;

import org.springframework.http.HttpStatus;

public interface Codable {

    String getMessage();
    HttpStatus getStatus();
    boolean getIsNotify();

}
