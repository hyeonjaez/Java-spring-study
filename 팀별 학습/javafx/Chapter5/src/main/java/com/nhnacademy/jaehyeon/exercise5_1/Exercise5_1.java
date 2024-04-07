package com.nhnacademy.jaehyeon.exercise5_1;

public class Exercise5_1 {

    public static void main(String[] args) {
        PairOfDice pairOfDice = new PairOfDice();

        int count = 0;

        while (true) {
            count++;
            pairOfDice.roll();
            int firstNum = pairOfDice.getDie1();
            int secondNum = pairOfDice.getDie2();
            System.out.println(pairOfDice.toString());
            if (firstNum + secondNum == 2) {
                break;

            }


        }
        System.out.printf("주사위의 값의 합이 2가 나올 때까지 %d회 굴렸습니다.\n", count);
    }

}
