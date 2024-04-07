package com.nhnacademy.jaehyeon;

/*
 * https://math.hws.edu/javanotes/c7/exercises.html
 * Write a program that will read a sequence of positive real numbers entered by the user
 * and will print the same numbers in sorted order from smallest to largest
 * The user will input a zero to mark the end of the input
 * Assume that at most 100 positive numbers will be entered.
 */
public class Exercise7_5 {

    public static void main(String[] args) {
        int[] array = {2, 31, 1, 3, 4, 5, 6, 1, 2, 3, 4};

        for (int number : sortBubble(array)) {
            System.out.println(number);
        }
    }

    public static int[] sortBubble(int[] arrays) {

        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays.length - 1; j++) {
                if (arrays[j] > arrays[j + 1]) {
                    int swap = arrays[j];
                    arrays[j] = arrays[j + 1];
                    arrays[j + 1] = swap;
                }
            }
        }
        return arrays;
    }

}