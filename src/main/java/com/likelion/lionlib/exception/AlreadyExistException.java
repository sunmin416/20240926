package com.likelion.lionlib.exception;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException() {
        super("이미 존재하는 이메일입니다.");
    }

    public AlreadyExistException(String email) {
        super("Email:" + email + "이미 존재하는 이메일입니다.");
    }
}

