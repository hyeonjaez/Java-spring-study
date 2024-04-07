package com.nhnacademy.jaehyeon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Exercise8_2 {
    private static final String YES = "yes";

    public static void main(String[] args) {
        boolean quitGame = true;
        Exercise8_2 exercise8_2 = new Exercise8_2();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (quitGame) {
                System.out.println("게임 진행을 더 하시길 원하신다면 'yes'라고 말해 주세요.");
                System.out.println("끝내실려면 아무거나 눌러주세요.");
                String input = br.readLine().toLowerCase().trim();
                if (!input.equals(YES)) {
                    System.out.println("끝");
                    quitGame = false;
                }

                String inputNumber = exercise8_2.inputNumber(br);
                BigInteger N = new BigInteger(inputNumber);
                System.out.println(exercise8_2.countSequenceCalculate(N));
            }
        } catch (IOException e) {
            System.out.println("입력 오류: " + e.getMessage());
        }
    }

    private String inputNumber(BufferedReader br) throws IOException {
        String inputNumber;
        System.out.println("숫자 입력하세요.");
        while (true) {
            inputNumber = br.readLine();
            if (isNumber(inputNumber)) {
                break;
            } else {
                System.out.println("유효한 숫자가 아님. 다시 입력하세요.");
            }
        }
        return inputNumber;
    }

    private boolean isNumber(String inputNumber) {
        try {
            Integer.parseInt(inputNumber);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("숫자로 입력해주세요.");
            return false;
        }
    }

    private int countSequenceCalculate(BigInteger N) {
        int count = 0;
        BigInteger number = N;
        while (!number.equals(new BigInteger("1"))) {
            count += 1;
            if (!number.testBit(0)) {
                number = number.divide(new BigInteger("2"));
            } else {
                number = number.multiply(new BigInteger("3")).add(new BigInteger("1"));
            }
        }
        return count;
    }
}