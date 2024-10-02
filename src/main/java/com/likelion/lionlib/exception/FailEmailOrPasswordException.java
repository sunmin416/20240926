package com.likelion.lionlib.exception;

public class FailEmailOrPasswordException extends RuntimeException {
    public FailEmailOrPasswordException() {
        super("email이나 password가 유효하지 않습니다.");
    }

    public FailEmailOrPasswordException(String email) {
        super("email이나 password가 유효하지 않습니다.");
    }
}
