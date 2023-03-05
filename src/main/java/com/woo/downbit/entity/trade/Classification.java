package com.woo.downbit.entity.trade;

import lombok.Data;

@Data
public enum Classification {
    SELL("매도"),
    BUY("매수");

    String value;

    Classification(String value) {
        this.value = value;
    }
}
