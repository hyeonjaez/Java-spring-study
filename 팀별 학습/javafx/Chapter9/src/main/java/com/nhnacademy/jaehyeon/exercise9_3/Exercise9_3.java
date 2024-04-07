package com.nhnacademy.jaehyeon.exercise9_3;

/*
* https://math.hws.edu/javanotes/c9/exercises.html
* 리스트 항목의 순서를 반대로 한 복사본을 생성하자 이 서브루틴은 ListNode 유형의 매개변수를 가져야 하며, ListNode 유형의 값을 반환 해야한다.
 */
public class Exercise9_3 {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        System.out.println(list);
        list.reverse();
        System.out.println(list);

    }
}