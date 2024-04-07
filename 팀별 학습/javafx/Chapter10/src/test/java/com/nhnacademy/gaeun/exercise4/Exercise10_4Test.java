package com.nhnacademy.gaeun.exercise4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class Exercise10_4Test {
    private List<String> fruits = new ArrayList<>();
    private ArrayList<Integer> values = new ArrayList<>();


    @Test
    @DisplayName("remove Test")
    void removeTest() {
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        Exercise10_4.remove(fruits, f -> f.equals("Orange"));
        Assertions.assertEquals(fruits.size(), 3);
    }

    @Test
    @DisplayName("retain Test")
    void retainTest() {
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        Exercise10_4.retain(fruits, f -> f.equals("Orange"));
        Assertions.assertEquals(fruits.size(), 1);
    }

    @Test
    @DisplayName("collect Test")
    void collectTest() {
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(5);
        values.add(6);
        values.add(7);
        Assertions.assertEquals(Exercise10_4.collect(values, n -> n % 2 == 0).size(),
                values.stream().filter(n-> n%2==0).count());
    }

    @Test
    @DisplayName("find Test")
    void findTest() {
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(5);
        values.add(6);
        values.add(7);
        Assertions.assertEquals(Exercise10_4.find(values, n -> n == 3),
                2);
    }


}