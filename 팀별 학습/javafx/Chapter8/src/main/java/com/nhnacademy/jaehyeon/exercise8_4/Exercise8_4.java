package com.nhnacademy.jaehyeon.exercise8_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Exercise8_4 {

    public static void main(String[] args) {
        boolean isQuit = true;


        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (isQuit) {
                List<Double> numbers = inputNumbers(br);
                inputExpression(br, numbers);

                System.out.println("끝낼거면 no를 입력해주시고 또 하실려면 아무거나 입력하세요");
                if ("no".equals(br.readLine().toLowerCase().trim())) {
                    isQuit = false;
                }
            }
        } catch (IOException e) {
            System.out.println("에러 발생");
        }
    }

    public static List<Double> inputNumbers(BufferedReader br) throws IOException {
        List<Double> numberList = new ArrayList<>();
        while (true) {
            System.out.println("숫자를 입력하세요");
            System.out.println("0 입력시 입력 끝");
            double input = inputNumber(br);
            if (input == 0) {
                break;
            }
            numberList.add(input);
        }

        return numberList;
    }

    public static double inputNumber(BufferedReader br) throws IOException {
        String x = "";
        while (true) {
            x = br.readLine();
            if (isNumber(x)) {
                break;
            }
        }
        return Double.parseDouble(x);
    }

    public static boolean isNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("숫자 입력하세요.");
            return false;
        }
    }

    public static void inputExpression(BufferedReader br, List<Double> numbers) throws IOException {

        while (true) {
            try {
                System.out.println("표현식을 입력해주세요");
                String expression = br.readLine().trim();
                Expr function = new Expr(expression);

                for (double number : numbers) {
                    System.out.printf("[%s] 에 x=%.3f 넣어 계산한 결과 : %.3f \n", expression, number, function.value(number));
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("다시 입력하세요.");
            }
        }
    }

}




