package com.nhnacademy.jaehyeon.exercise5_2;

import java.util.Scanner;

public class Exercise5_2 {

    public static void main(String[] args) {

        StatCalc statCalc = new StatCalc();
        inputNumber(statCalc);
        System.out.println(statCalc.toString());


    }

    public static void inputNumber(StatCalc statCalc) {
        Scanner scanner = new Scanner(System.in);
//todo  try-catch 릴리즈
        System.out.println("숫자를 입력하세요");
        System.out.println("0을 입력하면 입력을 멈춘다는것 입니다.");
        String num = scanner.nextLine();

        if (!isDoubleNumber(num)) {
            System.out.println("숫자만 입력해주세요");
            inputNumber(statCalc);
        } else if (Double.parseDouble(num) == 0) {
            scanner.close();
            return;
        } else {
            statCalc.enter(Double.parseDouble(num));
            inputNumber(statCalc);

        }


    }

    private static boolean isDoubleNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException num) {
            return false;
        }
    }
}

