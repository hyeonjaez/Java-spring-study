package com.nhnacademy.jaehyeon.exercise7_2;

import java.util.InputMismatchException;
import java.util.Scanner;


/*
 * https://math.hws.edu/javanotes/c7/exercises.html
 */

public class Exercise7_2 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("행의 값을 입력 하세요");
            int row = scanner.nextInt();
            verifyInputNumber(row);
            System.out.println("열의 값을 입력 하세요");
            int column = scanner.nextInt();
            verifyInputNumber(column);

            Array targetArray = new Array(row, column);
            TransposeArray transposeArray = new TransposeArray(targetArray);

            printArray(targetArray.getArray());
            System.out.println("============");
            printArray(transposeArray.getResultArray());
        } catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void printArray(int[][] array) {
        for (int[] numbers : array) {
            for (int num : numbers) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void verifyInputNumber(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("1이상의 값을 넣어 줘야 합니다.");
        }
    }
}
