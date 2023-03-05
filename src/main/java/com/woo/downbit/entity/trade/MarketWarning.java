package com.woo.downbit.entity.trade;

import lombok.Data;

@Data
public enum MarketWarning {
    NONE("해당 사항 없음"),
    CAUTION("투자 유의");

    String value;

    MarketWarning(String value) {
        this.value = value;
    }
}
