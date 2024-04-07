package com.nhnacademy.jaehyeon;

public class Problem4 {

    static final int ROLL_COUNT = 10_000;

    public static void main(String[] args) {
        System.out.println("주사위의 총합\t\t평균 굴림 횟수");
        System.out.println("----------\t\t-----------");
        for (int i = 2; i <= 12; i++) {
            System.out.println(i + "\t\t\t\t\t" + totalCount(i) / (double) ROLL_COUNT);
        }
    }


    public static double totalCount(int sumNumber) {
        int count = 0;
        double totalCount = 0;
        while (count != ROLL_COUNT) {
            totalCount++;
            int sum = sumDiceNumber();
            if (sum == sumNumber) {
                count++;
            }
        }

        return totalCount;
    }


    private static int diceNumber() {
        return (int) (Math.random() * 6) + 1;
    }

    private static int sumDiceNumber() {
        return diceNumber() + diceNumber();
    }

}


