package com.nhnacademy.jaehyeon;

import java.util.Scanner;

public class Exercise2_5 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Egg egg = new Egg(scan.nextInt());

        System.out.printf("계란 수는 총 %d개, 다스 %d개, %d개 입니다.", egg.getEggs(), egg.getDas(), egg.getEggNumber());
        scan.close();
    }
}
