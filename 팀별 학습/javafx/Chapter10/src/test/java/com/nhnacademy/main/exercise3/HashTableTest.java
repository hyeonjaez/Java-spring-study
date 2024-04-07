package com.nhnacademy.main.exercise3;

import com.nhnacademy.gaeun.exercise3.HashTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class HashTableTest {

    @Test
    @DisplayName("get test")
    void getTest() {
        HashTable hashTable = new HashTable(10);
        hashTable.put("오늘은", "일요일");
        hashTable.put("내일은", "월요일");
        hashTable.put("개구리", "왕눈이");
        hashTable.put("이클립스", "eclipse");
        hashTable.put("촉촉한", "초코칩");

        assertEquals(hashTable.get("개구리"), "왕눈이");
        assertEquals(hashTable.get("촉촉한"), "초코칩");
        assertThrows(IllegalArgumentException.class, () -> hashTable.get("메롱"));
    }

    @Test
    @DisplayName("put test")
    void putTest() {
        HashTable hashTable = new HashTable(10);
        hashTable.put("오늘은", "일요일");
        hashTable.put("내일은", "월요일");
        hashTable.put("개구리", "왕눈이");
        hashTable.put("이클립스", "eclipse");
        hashTable.put("촉촉한", "초코칩");

        assertEquals(hashTable.size(), 5);
        assertThrows(NullPointerException.class, () -> hashTable.put("", "abc"));
    }

    @Test
    @DisplayName("remove test")
    void removeTest() {
        HashTable hashTable = new HashTable(10);
        hashTable.put("오늘은", "일요일");
        hashTable.put("내일은", "월요일");
        hashTable.put("개구리", "왕눈이");
        hashTable.put("이클립스", "eclipse");
        hashTable.put("촉촉한", "초코칩");

        hashTable.remove("내일은");
        hashTable.remove("이클립스");
        assertEquals(hashTable.size(), 3);
        assertThrows(IllegalArgumentException.class, () -> hashTable.remove("abc"));
    }

    @Test
    @DisplayName("containsKey test")
    void containsKeyTest() {
        HashTable hashTable = new HashTable(10);
        hashTable.put("오늘은", "일요일");
        hashTable.put("내일은", "월요일");
        hashTable.put("개구리", "왕눈이");
        hashTable.put("이클립스", "eclipse");
        hashTable.put("촉촉한", "초코칩");

        assertEquals(hashTable.containsKey("개구리"), true);
        assertEquals(hashTable.containsKey("자바의 정석"), false);
        assertEquals(hashTable.containsKey("촉촉한"), true);
    }

    @Test
    @DisplayName("size test")
    void sizeTest() {
        HashTable hashTable = new HashTable(5);
        hashTable.put("오늘은", "일요일");
        hashTable.put("내일은", "월요일");
        hashTable.put("개구리", "왕눈이");
        hashTable.put("이클립스", "eclipse");
        hashTable.put("촉촉한", "초코칩");

        assertEquals(hashTable.size(), 5);
    }

}