package com.nhnacademy.jaehyeon.exercise10_4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise10_4 {

    static Collection<Integer> makeSet() {
        Collection<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(6);
        set.add(7);
        set.add(8);
        set.add(9);
        set.add(10);
        set.add(11);
        set.add(12);
        set.add(13);
        return set;
    }

    static Collection<String> makeStringSet() {
        Collection<String> set = new TreeSet<>();
        set.add("010-9417-3268");
        set.add("010-1234-3268");
        set.add("010-5678-3268");
        set.add("010-941-3268");
        set.add("010-97-3268");
        set.add("010-9417-368");
        set.add("010-9417-328");
        set.add("dfdf");
        return set;
    }

    static ArrayList<Integer> makeList() {
        List<Integer> list = List.of(3, 14, 63, 123, 1234, 234, 645, 365);
        ArrayList<Integer> arryaList = new ArrayList<>(list);
        return arryaList;
    }


    public static void main(String[] args) {
        Collection<Integer> before;
        Collection<String> strings;
        ArrayList<Integer> list;

        Predicate<Integer> isEvenNumber = obj -> obj % 2 == 0;
        before = makeSet();
        System.out.println("변경전");
        System.out.println(before.toString());
        PredicateMethod.remove(before, isEvenNumber);
        System.out.println("변경후");
        System.out.println(before.toString());
        System.out.println("==================");


        Predicate<Integer> isPrime = obj -> {
            for (int i = 2; i <= Math.sqrt(obj); i++) {
                if (obj % i == 0) {
                    return false;
                }
            }
            return true;
        };
        before = makeSet();
        System.out.println("변경전");
        System.out.println(before.toString());
        PredicateMethod.retain(before, isPrime);
        System.out.println("변경후");
        System.out.println(before.toString());
        System.out.println("==================");

        Predicate<String> isPhoneNumber = obj -> {
            Pattern pattern = Pattern.compile("^01[016789]-\\d{3,4}-\\d{4}$");
            Matcher matcher = pattern.matcher(obj);
            return matcher.matches();
        };
        strings = makeStringSet();
        System.out.println("변경전");
        System.out.println(strings.toString());
        System.out.println("전화번호 추출");
        System.out.println(PredicateMethod.collect(strings, isPhoneNumber).toString());
        System.out.println("==================");


        Predicate<Integer> isYearNumber = obj -> obj == 365;
        list = makeList();
        System.out.println("변경전");
        System.out.println(list.toString());
        System.out.println("365 숫자가 있는 index");
        System.out.println(PredicateMethod.find(list, isYearNumber));


    }
}
