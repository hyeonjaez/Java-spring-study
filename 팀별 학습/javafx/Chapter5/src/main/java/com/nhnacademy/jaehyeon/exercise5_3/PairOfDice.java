package com.nhnacademy.jaehyeon.exercise5_3;

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
        roll();
    }


    public void roll() {
        this.die1 = (int) (Math.random() * 6) + 1;
        this.die2 = (int) (Math.random() * 6) + 1;
    }

    public int rollDice(int answerNumber) {
        int rollingCount = 0;
        while (this.die1 + this.die2 != answerNumber) {
            rollingCount++;
            roll();

        }
        return rollingCount;
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


