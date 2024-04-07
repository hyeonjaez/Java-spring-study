package com.nhnacademy.jaehyeon;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Exercise8_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Exercise8_1 exercise8_1 = new Exercise8_1();
        exercise8_1.calculateEquation(scanner);
        scanner.close();
    }

    public void calculateEquation(Scanner scanner) {
        while (true) {
            try {
                System.out.println("no라고 입력하시면 프로그램은 끝납니다.");
                System.out.print("계속하고 싶으시면 no 말고 아무거나 입력해주세요: ");
                String inputNo = scanner.next();
                if (Objects.equals(inputNo, "no")) {
                    break;
                }
                System.out.print("A: ");
                double A = scanner.nextDouble();
                System.out.print("B: ");
                double B = scanner.nextDouble();
                System.out.print("C: ");
                double C = scanner.nextDouble();

                System.out.printf("입력하신 값의 답은 %d 입니다\n", root(A, B, C));

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력하세여");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public double root(double A, double B, double C) throws IllegalArgumentException {
        if (A == 0) {
            throw new IllegalArgumentException("A can't be zero.");
        } else {
            double disc = B * B - 4 * A * C;
            if (disc < 0) {
                throw new IllegalArgumentException("Discriminant < zero.");
            }
            return (-B + Math.sqrt(disc)) / (2 * A);
        }
    }
}


