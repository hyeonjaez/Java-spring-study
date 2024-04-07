package com.nhnacademy.jaehyeon;

public class Egg {

    private int eggNumber;

    public Egg(int eggNumber) {
        this.eggNumber = eggNumber;
    }

    public int getEggs() {
        int eggs = this.eggNumber / 144;
        this.eggNumber %= 144;
        return eggs;
    }

    public int getDas() {
        int das = this.eggNumber / 12;
        this.eggNumber %= 12;
        return das;
    }

    public int getEggNumber() {
        return this.eggNumber;
    }
}
