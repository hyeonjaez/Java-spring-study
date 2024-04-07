package com.nhnacademy.jaehyeon;

public enum Money {
    QUARTERS(25, "쿼터"),
    DIMES(10, "다임"),
    NICKELS(5, "니켈"),
    PENNIS(1, "페니");

    private int cent;
    private String name;

    Money(int cent, String name) {
        this.cent = cent;
        this.name = name;
    }

    public int calc(int amount) {
        return this.cent * amount;
    }

    public int getValue() {
        return this.cent;
    }

    public String getString() {
        return this.name;
    }

}

