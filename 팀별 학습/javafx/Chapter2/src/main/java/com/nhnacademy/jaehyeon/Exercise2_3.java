package com.nhnacademy.jaehyeon;

import java.util.Scanner;

public class Exercise2_3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("니 이름 뭐냐?");
        String name = scanner.nextLine();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < name.length(); i++) {
            if ('A' > name.charAt(i) || name.charAt(i) > 'Z') {
                sb.append((char) (name.charAt(i) - 32));
            } else {
                sb.append(name.charAt(i));
            }
        }

        System.out.printf("Hello, %s, 만나서 반가워요!", sb.toString());

        scanner.close();
    }
}
