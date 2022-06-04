package com.hwanld.EntripAPI.domain.exceptions;

public class NicknameOrUserIdNotValidException extends Exception{

    public NicknameOrUserIdNotValidException() {
    }

    public NicknameOrUserIdNotValidException(String message) {
        super(message);
    }
}
