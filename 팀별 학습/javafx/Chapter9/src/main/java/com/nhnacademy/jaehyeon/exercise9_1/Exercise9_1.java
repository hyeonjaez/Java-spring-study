package com.nhnacademy.jaehyeon.exercise9_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/*
 * https://math.hws.edu/javanotes/c9/exercises.html
 * 주어진 음이 아닌 정수 N에 대해 팩토리얼(N)과 피보나치(N)을 계산하기 위한 재귀 함수를 작성하고, 함수를 테스트하기 위한 main() 루틴을 작성하자.
 * BigInteger 클래스를 사용하는 것을 고려하자.
 */
public class Exercise9_1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String inputNumber = inputNumber(br);

            Formula formula = new MathFormula();

            BigInteger bigInteger = new BigInteger(inputNumber);

            MathCalculator factorialCalculate = formula::factorial;
            MathCalculator fibonacciCalculate = formula::fibonacci;

            System.out.println("팩토리얼 : " + factorialCalculate.calculate(bigInteger));
            System.out.println("피보나치 : " + fibonacciCalculate.calculate(bigInteger));


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String inputNumber(BufferedReader br) throws IOException {
        String number;
        while (true) {
            System.out.println("0 이상의 계산할 수를 입력하세요");
            number = br.readLine();

            if (isNumber(number) && verifyRangeNumber(number)) {
                break;
            }
        }
        return number;
    }

    public static boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean verifyRangeNumber(String number) {
        try {
            throwExceptionRangeNumber(number);
        } catch (NumberRangeException e) {
            return false;
        }
        return true;
    }

    private static void throwExceptionRangeNumber(String number) throws NumberRangeException {
        if (Integer.parseInt(number) <= 0) {
            throw new NumberRangeException("0보다 큰 수를 입력해야합니다.");
        }
    }


}
