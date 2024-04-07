package com.nhnacademy.jaehyeon;

import java.util.Arrays;

/*
 * https://math.hws.edu/javanotes/c7/exercises.html
 * should create a large array filled with random real numbers
 * It should use both Arrays.sort() and selectionSort() to sort the array
 * it should do the same thing for a large array of random Strings.
 */
public class Exercise7_3 {

    public static void main(String[] args) {
        int[] arrayTest = setArray();

        calculateSelectArray(arrayTest);
        calculateSortArray(arrayTest);
    }

    public static void calculateSelectArray(int[] array) {
        long beforeTime = System.currentTimeMillis();
        selectArray(array);
        long afterTime = System.currentTimeMillis();
        System.out.println("선택정렬 시간 : " + (afterTime - beforeTime));
    }


    public static void selectArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            int swap = array[minIndex];
            array[minIndex] = array[i];
            array[i] = swap;
        }
    }

    public static void calculateSortArray(int[] array) {
        long beforeTime = System.currentTimeMillis();
        sortArray(array);
        long afterTime = System.currentTimeMillis();
        System.out.println("함수정렬 시간 : " + (afterTime - beforeTime));
    }


    public static void sortArray(int[] array) {
        Arrays.sort(array);
    }

    public static int[] setArray() {
        int[] array = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 1000);
        }
        return array;
    }


}

