package com.nhnacademy.jaehyeon.exercise5_7;

public class SubtractionQuestion implements InQuestion {


    private int a, b;

    public SubtractionQuestion() {
        this.a = (int) (Math.random() * 50 + 1);
        this.b = (int) (Math.random() * 50);
    }

    @Override
    public String getQuestion() {
        return "What is " + this.a + " - " + this.b + " ?";
    }

    @Override
    public int getCorrectAnswer() {
        return this.a - this.b;
    }
}
