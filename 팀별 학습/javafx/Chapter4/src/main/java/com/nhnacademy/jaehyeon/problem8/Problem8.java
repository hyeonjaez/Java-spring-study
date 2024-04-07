package com.nhnacademy.jaehyeon.problem8;

import java.util.Scanner;


public class Problem8 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("문제를 몇개 생성 하시겠습니까");
        int questionSize = scanner.nextInt();
        Quiz quiz = new Quiz(questionSize);

        int correctNumber = printQuiz(quiz, questionSize);

        System.out.printf("최종 점수는 %d점 입니다.", calculatorScore(questionSize, correctNumber));

    }

    public static int printQuiz(Quiz quiz, int size) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int[] answerNumbers = new int[size];
        for (int i = 0; i < size; i++) {
            int firstNumber = quiz.getFirstNumbers()[i];
            int secondNumber = quiz.getSecondNumbers()[i];

            System.out.printf("%d + %d = ", firstNumber, secondNumber);
            answerNumbers[i] = firstNumber + secondNumber;
            quiz.getUserAnswers()[i] = scanner.nextInt();
        }

        return checkAnswer(quiz, answerNumbers);
    }

    public static int checkAnswer(Quiz quiz, int[] answer) {
        int countCorrect = 0;
        for (int i = 0; i < answer.length; i++) {
            if (quiz.getUserAnswers()[i] == answer[i]) {
                countCorrect++;
            }
        }

        return countCorrect;
    }

    public static int calculatorScore(int size, int correctNumber) {
        return (100 / size) * correctNumber;
    }

}


