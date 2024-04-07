package com.nhnacademy.jaehyeon;

import com.nhnacademy.textio.TextIO;

public class Exercise3_3 {

    public static void main(String[] args) {
        double firstNumber = TextIO.getDouble();
        if (firstNumber == 0) {
            throw new IllegalArgumentException("0 입력은 하지마!");
        }
        char operator = TextIO.getChar();
        if (operator == '+' || operator == '-' || operator == '*') {
            throw new IllegalArgumentException("연산자만 입력해라");
        }
        double secondNumber = TextIO.getDouble();

        double result = 0;
        if (operator == '+') {
            result = firstNumber + secondNumber;
        } else if (operator == '-') {
            result = firstNumber - secondNumber;
        } else if (operator == '*') {
            result = firstNumber * secondNumber;
        }

        System.out.println(result);
    }
}
