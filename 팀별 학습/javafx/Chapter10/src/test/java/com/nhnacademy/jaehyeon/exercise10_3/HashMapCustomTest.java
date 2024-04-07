package com.nhnacademy.jaehyeon.exercise10_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HashMapCustomTest {


    @DisplayName("constructor exception test")
    @Test
    void constructorTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new HashMapCustom(0));
    }

    @DisplayName("put() test")
    @Test
    void putTest() {
        //given
        HashMapCustom hashMapCustom = new HashMapCustom(100);

        //when
        hashMapCustom.put("재현", "jaehyeon");
        hashMapCustom.put("빛이 있으라", "fiat_lux");
        hashMapCustom.put("꽃", "flower");

        //then
        Assertions.assertEquals("jaehyeon", hashMapCustom.get("재현"));
        Assertions.assertEquals("fiat_lux", hashMapCustom.get("빛이 있으라"));
        Assertions.assertEquals("flower", hashMapCustom.get("꽃"));

    }

    @DisplayName("get null Test")
    @Test
    void getTest() {
        //given
        HashMapCustom hashMapCustom = new HashMapCustom(10);

        //when
        hashMapCustom.put("재현", "jaehyeon");

        //then
        Assertions.assertNull(hashMapCustom.get(""));
        Assertions.assertNull(hashMapCustom.get("hihi"));

    }

    @DisplayName("remove() test")
    @Test
    void removeTest() {
        //given
        HashMapCustom hashMapCustom = new HashMapCustom(10);

        //when
        hashMapCustom.put("재현", "jaehyeon");
        hashMapCustom.remove("재현");

        //then
        Assertions.assertNull(hashMapCustom.get("재현"));
    }

    @DisplayName("containsKey() test")
    @Test
    void containsKeyTest() {
        //given
        HashMapCustom hashMapCustom = new HashMapCustom(10);

        //when
        hashMapCustom.put("재현", "jaehyeon");
        hashMapCustom.put("빛이 있으라", "fiat_lux");
        hashMapCustom.put("꽃", "flower");

        //then
        Assertions.assertTrue(hashMapCustom.containsKey("재현"));
        Assertions.assertTrue(hashMapCustom.containsKey("빛이 있으라"));

        Assertions.assertFalse(hashMapCustom.containsKey("라벤더"));

    }
}