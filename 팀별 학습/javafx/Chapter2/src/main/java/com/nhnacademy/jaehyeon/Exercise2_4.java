package com.nhnacademy.jaehyeon;

import java.util.Scanner;

public class Exercise2_4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalCent = 0;

        for (Money money : Money.values()) {
            System.out.printf("%s 수:\n", money.getString());
            totalCent += money.calc(scanner.nextInt());
        }

        System.out.println(totalCent / (double) 100);

    }
}
