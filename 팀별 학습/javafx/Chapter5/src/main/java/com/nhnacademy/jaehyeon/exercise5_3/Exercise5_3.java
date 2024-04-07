package com.nhnacademy.jaehyeon.exercise5_3;

public class Exercise5_3 {

    static final int ROLL_COUNT = 10_000;

    public static void main(String[] args) {


        System.out.println("주사위의 총합\t\t\t평균 굴림 횟수\t\t표준편차\t\t\t\t\t\t\tMax");
        System.out.println("----------\t\t\t-----------\t\t-----\t\t\t\t\t\t\t-----");


        for (int i = 2; i <= 12; i++) {
            PairOfDice dices = new PairOfDice();
            StatCalc statCalc = new StatCalc();

            int count = 0;
            while (count != ROLL_COUNT) {
                count++;
                dices.roll();
                statCalc.enter(dices.rollDice(i));

            }

            System.out.println(
                    i + "\t\t\t\t\t" + (statCalc.getMean()) + "\t\t\t" + statCalc.getStandardDeviation()
                            + "\t\t\t\t" + statCalc.getMax());
        }

    }


}


