package com.nhnacademy.jaehyeon.exercise10_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

class PredicateMethodTest {
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

    static ArrayList<Integer> makeList() {
        List<Integer> list = List.of(3, 14, 63, 123, 1234, 234, 645, 365);
        ArrayList<Integer> arryaList = new ArrayList<>(list);
        return arryaList;
    }

    @DisplayName("remove() test")
    @Test
    void removeTest() {
        Collection<String> test = makeStringSet();
        PredicateMethod.remove(test, obj -> obj.equals("dfdf"));
        Assertions.assertFalse(test.contains("dfdf"));
    }


    @DisplayName("retain() test")
    @Test
    void retainTest() {
        Collection<String> test = makeStringSet();
        PredicateMethod.retain(test, obj -> obj.equals("010-9417-3268"));
        Assertions.assertTrue(test.contains("010-9417-3268"));
        Assertions.assertFalse(test.contains("dfdf"));
    }

    @DisplayName("collect() test")
    @Test
    void collectTest() {
        Collection<Integer> test = makeSet();
        Assertions.assertEquals(PredicateMethod.collect(test, number -> number % 2 == 0).size(),
                test.stream().filter(n -> n % 2 == 0).count());
    }

    @DisplayName("find() test")
    @Test
    void findTest() {
        ArrayList<Integer> test = makeList();
        Assertions.assertEquals(PredicateMethod.find(test, a -> a == 63), 2);
        Assertions.assertEquals(PredicateMethod.find(test, a -> a.equals(1)), -1);
    }


}