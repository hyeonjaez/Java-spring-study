package com.nhnacademy.jaehyeon;

import java.util.Scanner;

public class Problem2 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.next();
        System.out.println(hexValue(inputString));

        scanner.close();

    }

    public static int hexValue(String str) {
        int value = 0;
        for (int i = 0; i < str.length(); i++) {
            int num = 0;

            if (('0' <= str.charAt(i) && str.charAt(i) <= '9')) {
                num = str.charAt(i) - '0';
                switch (num) {
                    case 0:
                        num = 0;
                        break;

                    case 1:
                        num = 1;
                        break;

                    case 2:
                        num = 2;
                        break;

                    case 3:
                        num = 3;
                        break;

                    case 4:
                        num = 4;
                        break;

                    case 5:
                        num = 5;
                        break;

                    case 6:
                        num = 6;
                        break;

                    case 7:
                        num = 7;
                        break;

                    case 8:
                        num = 8;
                        break;

                    case 9:
                        num = 9;
                        break;

                }

            } else {
                switch (str.charAt(i)) {
                    case 'A':
                    case 'a':
                        num = 10;
                        break;
                    case 'B':
                    case 'b':
                        num = 11;
                        break;
                    case 'C':
                    case 'c':
                        num = 12;
                        break;
                    case 'D':
                    case 'd':
                        num = 13;
                        break;
                    case 'E':
                    case 'e':
                        num = 14;
                        break;
                    case 'F':
                    case 'f':
                        num = 15;
                        break;
                    default:
                        num = -1;
                        break;
                }

            }

            if (num == -1) {
                return -1;
            }

            value += (int) calculateDigit(str.length() - 1 - i, num);
        }
        return value;
    }

    public static double calculateDigit(int i, int num) {
        return num * Math.pow(16, i);
    }


}


