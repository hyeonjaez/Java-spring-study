package com.nhnacademy.jaehyeon;

import java.util.Arrays;

public class Exercise3_7 {

    public static void main(String[] args) {
        //3.7.1

        int[] days = new int[366];
        int count = 0;

        for (int i = 0; i < days.length; i++) {
            int day = getRandomNumber();

            count++;
            days[day]++;
            if (days[day] == 3) {
                break;
            }
        }

        System.out.println(count + "번 해야 생일이 같은 세명의 사람을 찾을수 있다");

        //3.7.2
        int[] birthday = new int[366];
        for (int i = 0; i < 366; i++) {
            int day = getRandomNumber();
            birthday[day]++;
        }
        int cnt = -1;
        for (int day : birthday) {
            if (day == 0) {
                cnt++;
            }
        }

        System.out.println(365 - cnt + "이정도로 다른 생일들을 가지고 있습니다.");


        //3.7.3
        int[] birthdays = new int[366];

        int cot = 0;
        birthdays[0] = 1;

        while (true) {
            cot++;
            int day = getRandomNumber();
            birthdays[day]++;

            if (!used(birthdays)) {
                break;
            }

        }

        System.out.printf("각각마다 생일이 있는 사람을 적어도 한명을 찾으려면 %d 정도 확인해야한다", cot);
    }

    public static boolean used(int[] dayCount) {

        return Arrays.stream(dayCount).anyMatch(visitCount -> visitCount == 0);
    }


    public static int getRandomNumber() {
        return (int) (Math.random() * 365 + 1);
    }
}
