package com.nhnacademy.jaehyeon.exercise5_6;

import java.util.Scanner;

public class Exercise5_6 {

    public static void main(String[] args) {
        System.out.println("=====Start Quiz=====");
        quiz();
    }

    public static void quiz() {

        Scanner scanner = new Scanner(System.in);
        int count = 0;
        for (int i = 0; i < 10; i++) {
            AdditionQuestion additionQuestion = new AdditionQuestion();

            System.out.println(additionQuestion.getQuestion());
            int userAnswer = inputNumber();
            if (verifyAnswer(userAnswer, additionQuestion.getCorrectAnswer())) {
                count++;
            }
        }

        System.out.printf("총 점수는 %d점 입니다.", count);

        scanner.close();

    }

    private static int inputNumber() {
        Scanner scanner = new Scanner(System.in);
        String getNumber;
        while (true) {
            getNumber = scanner.nextLine();
            if (!isNumber(getNumber)) {
                System.out.println("정수만 입력 바랍니다.");

            } else {
                break;
            }
        }

        return Integer.parseInt(getNumber);
    }

    private static boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException num) {
            return false;
        }
    }


    private static boolean verifyAnswer(int userAnswer, int correctAnswer) {
        if (userAnswer == correctAnswer) {
            System.out.println("정답입니다");
            return true;
        } else {
            System.out.printf("땡 틀렸습니다 정답은 %d입니다\n", correctAnswer);
        }

        return false;
    }
}


