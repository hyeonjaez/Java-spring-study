package com.nhnacademy.jaehyeon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Exercise3_2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int finishNumber = scanner.nextInt();
        if (finishNumber <= 1) {
            throw new IllegalArgumentException("2이상의 숫자만 넣어주세여");
        }

        List<Number> list = new ArrayList<>();

        for (int i = 1; i <= finishNumber; i++) {
            list.add(new Number(i, count(i)));
        }

        Number maxCount = list.stream()
                .max(Comparator.comparingInt(Number::getCount))//
                .orElseThrow();

        System.out.printf("1과 %d 사이의 정수 중에서,\n", finishNumber);
        System.out.printf("약수의 최대 갯수는 %d\n", maxCount.getCount());
        System.out.println("이러한 약수의 갯수를 가진 숫자는:" + maxCount.getNumber());

        scanner.close();
    }

    private static int count(int num) {
        int cnt = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                cnt++;
            }
        }
        return cnt;
    }
}

