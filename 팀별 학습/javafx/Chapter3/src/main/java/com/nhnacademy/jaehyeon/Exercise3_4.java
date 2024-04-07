package com.nhnacademy.jaehyeon;

import java.util.Scanner;

public class Exercise3_4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String letters = scanner.nextLine();
        String[] split_Letters = letters.split(" ");
        for (String str : split_Letters) {
            String regex = str.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "");
            System.out.println(regex);
        }
    }
}
