package com.nhnacademy.jaehyeon.exercise5_1;

public class PairOfDice {

    private int die1;
    private int die2;

    public int getDie1() {
        return die1;
    }

    public int getDie2() {
        return die2;
    }

    public PairOfDice() {

    }

    public void roll() {
        this.die1 = (int) (Math.random() * 6) + 1;
        this.die2 = (int) (Math.random() * 6) + 1;
    }

    @Override
    public String toString() {
        return "PairOfDice{" +
                "die1=" + die1 +
                ", die2=" + die2 +
                '}';
    }

    public boolean isPair() {
        return this.die1 == this.die2;
    }

}


