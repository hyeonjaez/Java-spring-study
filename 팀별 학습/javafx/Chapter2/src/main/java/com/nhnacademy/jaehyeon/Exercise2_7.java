package com.nhnacademy.jaehyeon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Exercise2_7 {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/hyeon/Desktop/default_project/JaeHyeon/chapter2/testdata.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String name = reader.readLine();
        int score1 = Integer.parseInt(reader.readLine());
        int score2 = Integer.parseInt(reader.readLine());
        int score3 = Integer.parseInt(reader.readLine());

        double avgScore = (score1 + score2 + score3) / 3.0;

        System.out.println("이름 : " + name + " 평균점수 : " + avgScore);

    }
}
