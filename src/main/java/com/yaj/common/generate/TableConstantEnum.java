package com.yaj.common.generate;

import java.util.List;

public enum TableConstantEnum {
    TYPE("type"),
    STATUS("status");

    private String value;
    private TableConstantEnum(String value) {
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
