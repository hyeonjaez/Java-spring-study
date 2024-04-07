package com.nhnacademy.jaehyeon.exercise8_3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise8_3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("로마 문자 입력하세요");
                String inputString = scanner.next();
                RomaNumberConverter romaNumberConverter = new RomaNumberConverter(inputString);
                System.out.println(romaNumberConverter.convertRomaToNumber());

                System.out.println("숫자 입력하세요");
                int inputNumber = scanner.nextInt();
                RomaNumberConverter romaNumberConverter1 = new RomaNumberConverter(inputNumber);
                System.out.println(romaNumberConverter1.convertNumberToRoma());
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("숫자 입력하세요");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}


