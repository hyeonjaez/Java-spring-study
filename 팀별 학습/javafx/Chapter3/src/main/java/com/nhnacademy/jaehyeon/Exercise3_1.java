package com.nhnacademy.jaehyeon;

public class Exercise3_1 {

    public static void main(String[] args) {
        int cnt = 0;

        while (true) {
            cnt++;
            int firstNumber = diceNumber();
            int secondNumber = diceNumber();
            if (firstNumber == 1 && secondNumber == 1) {
                break;
            }

        }
        System.out.printf("총 %d회 굴렸습니다.\n", cnt);
    }

    public static int diceNumber() {
        return (int) (Math.random() * 6) + 1;
    }

}
