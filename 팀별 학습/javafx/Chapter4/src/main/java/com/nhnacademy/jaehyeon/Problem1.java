package com.nhnacademy.jaehyeon;

import java.util.Scanner;

public class Problem1 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("문자를 입력해주세요");
        String str = scanner.nextLine();

        printCapitalized(str);
    }

    public static void printCapitalized(String str) {
        StringBuilder sb = new StringBuilder();
        String[] splitString = splitString(str);

        for (int i = 0; i < splitString.length; i++) {
            sb.append(upperFirstString(splitString[i]));
            if (i != splitString.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    public static StringBuilder upperFirstString(String str) {
        StringBuilder sb = new StringBuilder();

        if (Character.isLetter(str.charAt(0))) {
            if ('a' <= str.charAt(0) && str.charAt(0) <= 'z') {
                sb.append(Character.toUpperCase(str.charAt(0)));
            } else {
                sb.append(str.charAt(0));
            }
        } else {
            sb.append(str.charAt(0));
        }

        for (int i = 1; i < str.length(); i++) {
            sb.append(str.charAt(i));
        }

        return sb;
    }


    private static String[] splitString(String str) {
        return str.split(" ");
    }


}


