package com.woo.downbit.entity.user;

public enum Role {

    ROLE_USER("유저"),
    ROLE_ADMIN("관리자");

    String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
