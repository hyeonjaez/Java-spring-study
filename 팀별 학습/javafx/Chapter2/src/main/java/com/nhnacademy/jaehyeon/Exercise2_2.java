package com.nhnacademy.jaehyeon;

import java.util.Scanner;

public class Exercise2_2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("주사위 몇번 돌릴까요? :");

        int count = scan.nextInt();
        if (count <= 1) {
            throw new IllegalArgumentException("주사위는 최소 2번 이상은 굴려야 합니다.");
        }
        int[] diceNumbers = new int[count];
        int sum = 0;
        for (int i = 0; i < count; i++) {
            int diceNumber = getDiceNumber();
            diceNumbers[i] = diceNumber;
            System.out.printf("%d 번째 주사위가 %d이 나왔습니다.\n", i + 1, diceNumber);
            sum += diceNumber;
        }

        System.out.printf("총 주사위의 값은 %d 입니다.", sum);
        scan.close();
    }

    public static int getDiceNumber() {
        return (int) (Math.random() * 6) + 1;
    }
}
