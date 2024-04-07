package com.nhnacademy.jaehyeon.problem8;

public class Quiz {
    int[] firstNumbers;
    int[] secondNumbers;
    int[] userAnswers;

    public Quiz(int size) {
        setSizeArray(size);
        setQuizNumber(size);
    }


    public void setSizeArray(int size) {
        firstNumbers = new int[size];
        secondNumbers = new int[size];
        userAnswers = new int[size];
    }

    public void setQuizNumber(int size) {
        for (int i = 0; i < size; i++) {
            this.firstNumbers[i] = (int) (Math.random() * 100);
            this.secondNumbers[i] = (int) (Math.random() * 100);
        }
    }

    public int[] getFirstNumbers() {
        return firstNumbers;
    }

    public int[] getSecondNumbers() {
        return secondNumbers;
    }

    public int[] getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(int index, int answer) {
        this.userAnswers[index] = answer;
    }
}
