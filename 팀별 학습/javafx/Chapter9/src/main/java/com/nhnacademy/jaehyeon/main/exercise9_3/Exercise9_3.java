package com.nhnacademy.jaehyeon.main.exercise9_3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * https://math.hws.edu/javanotes/c9/exercises.html
 * 리스트 항목의 순서를 반대로 한 복사본을 생성하자 이 서브루틴은 ListNode 유형의 매개변수를 가져야 하며, ListNode 유형의 값을 반환 해야한다.
 */
public class Exercise9_3 {
    public static void main(String[] args) {
        CustomLinkedList<Integer> list;

        list = new CustomLinkedList<>();
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);

        final Iterator<Integer> originIter = list.iterator();
        final Iterator<Integer> reverseIter = list.reverse().iterator();

        //출력

        try {
            System.out.println("원본");
            while (originIter.hasNext()) {
                System.out.println(originIter.next());
            }
            System.out.println("역변환");
            while (reverseIter.hasNext()) {
                System.out.println(reverseIter.next());
            }
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            System.out.println("(그럴 리는 없겠지만) next로 값을 가져올 수 없습니다!");
        }
    }
}

