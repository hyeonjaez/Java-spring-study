package com.nhnacademy.jaehyeon.exercise10_5;

public class ScoreInfo {
    private final String lastName;
    private final String firstName;
    private final int score;

    public ScoreInfo(String lastName, String firstName, int score) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.score = score;
    }


    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getScore() {
        return score;
    }

    @SuppressWarnings("checkstyle:OperatorWrap")
    @Override
    public String toString() {
        return "ScoreInfo{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", score=" + score +
                '}';
    }
}