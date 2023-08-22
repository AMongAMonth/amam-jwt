package com.amam.amamjwt.exception.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorContent {

    private String message;

    public ErrorContent(String message) {
        this.message = message;
    }

}
