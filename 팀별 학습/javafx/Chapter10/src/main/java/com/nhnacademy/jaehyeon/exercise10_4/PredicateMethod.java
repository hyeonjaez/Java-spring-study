package com.nhnacademy.jaehyeon.exercise10_4;

/*
 * https://math.hws.edu/javanotes/c10/exercises.html
 * Java에서 조건자를 사용하는 일반 정적 메서드를 가지고 있는 클래스를 작성합니다.
 * 이 클래스는 컬렉션 작업을 위한 다양한 메서드를 제공하며, 클래스 이름은 Predicates와 유사하게 지어야 합니다.
 * 스트림 API를 사용해서는 안 됩니다.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PredicateMethod {
    public static <T> void remove(Collection<T> coll, Predicate<T> pred) {
        Iterator<T> iterator = coll.iterator();
        while (iterator.hasNext()) {
            T object = iterator.next();
            if (pred.test(object)) {
                iterator.remove();
            }
        }
    }

    public static <T> void retain(Collection<T> coll, Predicate<T> pred) {
        Iterator<T> iterator = coll.iterator();
        while (iterator.hasNext()) {
            T object = iterator.next();
            if (!pred.test(object)) {
                iterator.remove();
            }
        }
    }

    public static <T> List<T> collect(Collection<T> coll, Predicate<T> pred) {
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = coll.iterator();
        while (iterator.hasNext()) {
            T object = iterator.next();
            if (pred.test(object)) {
                list.add(object);
            }
        }
        return list;
    }

    public static <T> int find(ArrayList<T> list, Predicate<T> pred) {
        for (T item : list) {
            if (pred.test(item)) {
                return list.indexOf(item);
            }
        }
        return -1;
    }
}
