package com.likelion.lionlib.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("책을 찾을 수 없습니다.");
    }
}