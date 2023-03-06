package com.woo.downbit.entity.trade;


public enum Classification {
    SELL("매도"),
    BUY("매수");

    String value;

    Classification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
