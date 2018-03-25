package com.avenuecode.orders.specification;

import lombok.Getter;

@Getter
public class SearchCriteria {
    String key;
    String operation;
    Object value;

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}