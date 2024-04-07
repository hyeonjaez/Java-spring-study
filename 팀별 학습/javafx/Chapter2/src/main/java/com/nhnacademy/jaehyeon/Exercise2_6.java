package com.nhnacademy.jaehyeon;

import java.util.Scanner;

public class Exercise2_6 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        int index = name.indexOf(" ");

        String firstName = name.substring(0, index);
        String lastName = name.substring(index + 1);

        System.out.printf("귀하의 이름은 %s(%d자)입니다.\n", lastName, lastName.length());
        System.out.printf("성은 %s(%d자) 입니다.\n", firstName, firstName.length());
        System.out.printf("이니셜은 %s%s 입니다.", firstName.charAt(0), lastName.charAt(0));

        scan.close();
    }
}
