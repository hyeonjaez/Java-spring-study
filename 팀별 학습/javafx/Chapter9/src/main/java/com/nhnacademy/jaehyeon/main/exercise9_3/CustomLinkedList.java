package com.nhnacademy.jaehyeon.main.exercise9_3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CustomLinkedList는 제네릭한 형태의 링크드 리스트 자료 구조를 나타냅니다.
 * 이 클래스는 리스트의 길이 계산, 요소 조회, 값 추가, 리스트 역순으로 출력, 반복자 등의 기능을 제공합니다.
 *
 * @param <T> 리스트 요소의 형식을 나타내는 제네릭 타입입니다.
 */
public class CustomLinkedList<T> {


    ListNode<T> head;

    /**
     * CustomLinkedList의 새 인스턴스를 생성합니다.
     * 리스트는 초기에 비어있습니다.
     */
    public CustomLinkedList() {
        this.head = null;
    }

    /**
     * 리스트의 길이를 반환합니다.
     *
     * @return 리스트의 길이
     */
    public int length() {
        int length;
        if (head == null) {
            return 0;
        } else {
            length = 1;
            Iterator<T> iter = this.iterator();
            while (iter.hasNext()) {
                iter.next();
                length++;
            }
        }
        return length;
    }

    /**
     * 주어진 인덱스 위치에 있는 요소를 반환합니다.
     *
     * @param index 조회할 요소의 인덱스 (0부터 시작)
     * @return 주어진 인덱스 위치의 요소
     * @throws IndexOutOfBoundsException 인덱스가 리스트 범위를 벗어나면 발생
     */
    public T get(int index) {
        if (index + 1 > this.length()) {
            throw new IndexOutOfBoundsException("인덱스 범위를 초과했습니다.");
        }
        ListNode<T> result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    /**
     * 리스트에 새로운 요소를 추가합니다.
     *
     * @param item 추가할 요소
     */
    public void add(T item) {
        ListNode<T> newNode = new ListNode<>(item);
        if (this.head == null) {
            this.head = newNode;
        } else {
            ListNode<T> tempNode = this.head;
            while (tempNode.next != null) {
                tempNode = tempNode.next;
            }
            tempNode.next = newNode;
        }
    }

    /**
     * 리스트를 역순으로 출력한 결과를 반환합니다.
     *
     * @return 리스트를 역순으로 출력한 CustomLinkedList 인스턴스
     */
    public CustomLinkedList<T> reverse() {
        if (this.head == null) {
            return new CustomLinkedList<>();
        }
        CustomLinkedList<T> tempList = new CustomLinkedList<>();
        for (int i = this.length() - 1; i >= 0; i--) {
            tempList.add(this.get(i));
        }
        return tempList;
    }

    /**
     * 컬렉션에서 반복 작업을 수행하기 위한 반복자를 반환합니다.
     *
     * @return 컬렉션을 반복하는 데 사용되는 Iterator 인터페이스 구현
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int nextIndex = 0;

            @Override
            public boolean hasNext() {
                return nextIndex < length();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(nextIndex++);
            }
        };
    }

    private static class ListNode<T> {
        T item;
        ListNode<T> next;

        /**
         * 리스트 노드를 생성합니다.
         *
         * @param item 노드에 저장할 요소
         */
        public ListNode(T item) {
            this.item = item;
            this.next = null;
        }
    }
}
