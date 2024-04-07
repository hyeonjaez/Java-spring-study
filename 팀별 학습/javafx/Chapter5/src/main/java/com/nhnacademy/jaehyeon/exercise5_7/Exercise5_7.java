package com.nhnacademy.jaehyeon.exercise5_7;

import java.util.Scanner;

public class Exercise5_7 {

    public static void main(String[] args) {
        InQuestionList inQuestionList = new InQuestionList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("덧셈 문제 몇 문제를 추가 하겠습니까?");
        int addQuestionNumber = scanner.nextInt();
        verifyQuestionNumber(addQuestionNumber);
        addAdditionQuiz(inQuestionList, addQuestionNumber);

        System.out.println("뺄셈 문제 몇 문제를 추가 하겠습니까?");
        int subQuestionNumber = scanner.nextInt();
        verifyQuestionNumber(subQuestionNumber);
        addSubQuiz(inQuestionList, subQuestionNumber);

        int totalQuestionNumber = addQuestionNumber + subQuestionNumber;

        System.out.println("====================");
        System.out.println("======문제 입니다======");
        System.out.println("====================");

        int correct = printQuestion(inQuestionList, scanner);
        System.out.printf("총 점수는 %d점 입니다", calculateScore(totalQuestionNumber, correct));

        scanner.close();
    }


    private static void addAdditionQuiz(InQuestionList inQuestionList, int num) {
        for (int i = 0; i < num; i++) {
            addQuestion(inQuestionList, new AdditionQuestion());
        }
    }

    private static void addSubQuiz(InQuestionList inQuestionList, int num) {
        for (int i = 0; i < num; i++) {
            addQuestion(inQuestionList, new SubtractionQuestion());
        }
    }

    private static void addQuestion(InQuestionList inQuestionList, InQuestion inQuestion) {
        inQuestionList.addQuestion(inQuestion);

    }

    private static int printQuestion(InQuestionList inQuestionList, Scanner scanner) {
        int countCorrect = 0;
        for (InQuestion inQuestion : inQuestionList.getQuestionList()) {
            System.out.println(inQuestion.getQuestion());
            int userAnswer = scanner.nextInt();
            if (isCorrectAnswer(inQuestion, userAnswer)) {
                System.out.println("정답 입니다.");
                countCorrect++;
            } else {
                System.out.printf("땡 틀렸습니다. 정답은 %d 입니다.\n", inQuestion.getCorrectAnswer());
            }
        }

        return countCorrect;
    }

    private static boolean isCorrectAnswer(InQuestion inQuestion, int userAnswer) {
        return inQuestion.getCorrectAnswer() == userAnswer;
    }

    private static int calculateScore(int questionNumber, int correctNumber) {
        int oneScore = 100 / questionNumber;
        return oneScore * correctNumber;
    }

    private static void verifyQuestionNumber(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("0이상의 값을 넣어주세요");
        }
    }


}

