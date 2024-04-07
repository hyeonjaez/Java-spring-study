package com.nhnacademy.jaehyeon;

import java.util.Scanner;

public class Problem3 {

    public static int diceNumber() {
        return (int) (Math.random() * 6) + 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int inputNumber = scanner.nextInt();
        verifySumRange(inputNumber);

        playDiceGame(inputNumber);

        scanner.close();
    }


    public static void playDiceGame(int inNum) {
        int count = 0;
        while (true) {
            count++;
            int sum = diceNumber() + diceNumber();
            if (sum == inNum) {
                break;
            }
        }
        System.out.printf("입력한 값 %d가 나올때까지 걸린 횟수 : %d회", inNum, count);
    }


    public static void verifySumRange(int sum) {
        if (2 > sum || sum > 12) {
            throw new IllegalArgumentException("숫자가 잘못 되었습니다.");
        }
    }


}