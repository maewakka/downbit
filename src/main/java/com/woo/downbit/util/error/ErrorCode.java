package com.woo.downbit.util.error;

public enum ErrorCode {

    USER_NOT_FOUND(41, "유저를 찾을 수 없습니다.");

    int code;
    String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
