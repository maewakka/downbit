package com.woo.downbit.entity.user;

import lombok.Data;

@Data
public enum Role {

    ROLE_USER("유저"),
    ROLE_ADMIN("관리자");

    String value;

    Role(String value) {
        this.value = value;
    }
}
