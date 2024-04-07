package com.nhnacademy.jaehyeon.exercise10_3;

import java.util.LinkedList;


public class HashMapCustom {

    private LinkedList<Entry>[] table;
    private int size;


    public HashMapCustom(int initialSize) {
        this.table = new LinkedList[initialSize];
        if (initialSize <= 0) {
            throw new IllegalArgumentException("0보다 큰 수 넣으세요");
        }
        this.table = new LinkedList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            this.table[i] = new LinkedList<>();
        }

    }

    public void put(String key, String value) {
        if (key == null) {
            return;
        }
        int index = getIndex(key);
        LinkedList<Entry> list = table[index];
        for (Entry entry : list) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        list.add(new Entry(key, value));
        size++;


    }

    public String get(String key) {
        if (key == null) {
            return null;
        }
        int index = getIndex(key);
        LinkedList<Entry> list = table[index];
        for (Entry entry : list) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public int getIndex(String key) { // key에 대한 해시 코드를 생성하고 그 해시 코드를 해시 테이블의 크기에 맞게 인덱스로 변환하는 역할을 한다.
        return (key.hashCode() & 0x7FFFFFFF) % table.length;
    }
    // key.hascode() => key객체의 해시 코드를 얻어옴
    // & 0x7FFFFFF => key의 해시코드에서 부호 비트를 제거하여 음수가 아닌 정수로 만든다. 자바의 해시코드 메서드는 음수를 포함할 수 있기 때문에
    // 이 부분은 부호 비트를 제거하여 음수를 양수로 바꿔주는 역할을 한다.
    //% table.length => 해시 코드를 현재 해시 테이블의 크기로 모듈러 연산을 수행하여 실제로 데이터를 저장할 배열의 위치를 결정함
    // 0x7FFFFFFF => 16진수  이 값은 32비트 정수의 최상위 비트를 제외한 비트가 1이고, 최상위 비트가 0인 숫자를 나타냄.
    // 이 값은 주로 부호 있는 정수를 부호 없는 정수로 바꿀때 사용함
    // 0x는 16진수를 나타내는 접두사이고, 7FFFFFFF 는 16진수로 0111 1111 1111 1111 1111 1111 1111 1111 이라는 이진 숫자다
    // 이는 부호 없는 정수로 변환되며 약 21억까지의 양수를 나타낼수 있음

    public void remove(String key) {
        int index = getIndex(key);
        LinkedList<Entry> list = table[index];
        Entry removeEntry = null;
        for (Entry entry : list) {
            if (entry.getKey().equals(key)) {
                removeEntry = entry;
                break;
            }
        }
        if (removeEntry != null) {
            list.remove(removeEntry);
            this.size--;

        }
    }

    public boolean containsKey(String key) {
        int index = getIndex(key);
        LinkedList<Entry> list = table[index];
        for (Entry e : list) {
            if (e.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }


    public int size() {
        return this.size;
    }


    private static class Entry {
        private String key;
        private String value;

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
