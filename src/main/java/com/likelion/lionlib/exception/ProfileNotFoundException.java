package com.likelion.lionlib.exception;

import com.likelion.lionlib.domain.Member;

public class ProfileNotFoundException extends RuntimeException{

    public ProfileNotFoundException(Member member) {
        super("Profile를 찾을 수 없습니다.");
    }
}
