package com.accenture.codingtest.springbootcodingtest.enums;

public enum Roles {

    ADMIN("ADMIN"),
    PRODUCT_OWNER("PRODUCT_OWNER");

    private String value;

    private Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}