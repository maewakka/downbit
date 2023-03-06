package com.woo.downbit.entity.user;

public enum Role {

    ROLE_USER("ROLE_USER", "유저"),
    ROLE_ADMIN("ROLE_ADMIN", "관리자");

    String key;
    String value;

    Role(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
