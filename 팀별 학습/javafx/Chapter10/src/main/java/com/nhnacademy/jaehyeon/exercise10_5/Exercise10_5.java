package com.nhnacademy.jaehyeon.exercise10_5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * https://math.hws.edu/javanotes/c10/exercises.html
 * 학생 수를 스트림 API를 사용하여 출력.
 * 모든 학생의 평균 점수를 스트림 API를 사용하여 출력.
 * 90점 이상을 받은 학생의 수를 스트림 API를 사용하여 출력.
 * Collect() 스트림 작업을 사용하여 70점 미만인 학생의 이름이 포함된 List<String>을 생성하고, 이름은 이름 뒤에 성의 형식이어야 함.
 * 이전 작업에서 생성된 목록의 이름을 출력.
 * 성순으로 정렬하여 학생의 이름과 점수를 스트림 API를 사용하여 출력.
 * 점수순으로 정렬하여 학생의 이름과 점수를 스트림 API를 사용하여 출력.
 */
public class Exercise10_5 {

    public static void main(String[] args) {
        System.out.println("학생 수: " + studentNumber());
        System.out.println("학생들의 평균 점수: " + calculateStudentAverageScore());
        System.out.println("A학점을 받은 학생 수: " + receivedAGradeCount());
        System.out.println("score가 70미만인 학생들 리스트 생성: ");
        printScoreLessThan70Student();
        System.out.println("lastName을 기준으로 정렬");
        printSortLastName();
        System.out.println("score을 기준으로 정렬");
        printSortScore();
    }

    private static final ScoreInfo[] scoreData = new ScoreInfo[]{
            new ScoreInfo("Smith", "John", 70),
            new ScoreInfo("Doe", "Mary", 85),
            new ScoreInfo("Page", "Alice", 82),
            new ScoreInfo("Cooper", "Jill", 97),
            new ScoreInfo("Flintstone", "Fred", 66),
            new ScoreInfo("Rubble", "Barney", 80),
            new ScoreInfo("Smith", "Judy", 48),
            new ScoreInfo("Dean", "James", 90),
            new ScoreInfo("Russ", "Joe", 55),
            new ScoreInfo("Wolfe", "Bill", 73),
            new ScoreInfo("Dart", "Mary", 54),
            new ScoreInfo("Rogers", "Chris", 78),
            new ScoreInfo("Toole", "Pat", 51),
            new ScoreInfo("Khan", "Omar", 93),
            new ScoreInfo("Smith", "Ann", 95)
    };

    public static long studentNumber() {
        return Arrays.stream(scoreData).count();
    }

    public static double calculateStudentAverageScore() {
        return Arrays.stream(scoreData)
                .mapToDouble(ScoreInfo::getScore)
                .average()
                .orElse(0.0);
    }

    public static long receivedAGradeCount() {
        return Arrays.stream(scoreData)
                .filter(scoreInfo -> scoreInfo.getScore() >= 90)
                .count();
    }

    public static List<String> scoreLessThan70() {
        return Arrays.stream(scoreData)
                .filter(scoreInfo -> scoreInfo.getScore() < 70)
                .map(scoreInfo -> scoreInfo.getFirstName() + scoreInfo.getLastName())
                .collect(Collectors.toList());
    }

    public static void printScoreLessThan70Student() {
        scoreLessThan70().stream()
                .forEach(System.out::println);
    }

    public static void printSortLastName() {
        Arrays.stream(scoreData)
                .sorted(Comparator.comparing(ScoreInfo::getLastName))
                .forEach(System.out::println);
    }

    public static void printSortScore() {
        Arrays.stream(scoreData)
                .sorted(Comparator.comparingInt(ScoreInfo::getScore))
                .map(ScoreInfo::toString)
                .forEach(System.out::println);
    }


}
