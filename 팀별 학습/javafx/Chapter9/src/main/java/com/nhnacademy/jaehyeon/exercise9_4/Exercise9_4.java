package com.nhnacademy.jaehyeon.exercise9_4;

/*
* 전에는 이진 트리의 항목을 다양한 순서로 출력하는 데 재귀를 사용하는 방법을 했다.
* 이번에는 보조 데이터 구조로 스택 또는 큐를 사용하는 경우 비재귀 서브루틴을 사용할 수 있다고 한다.
* 큐를 사용하는 서브루틴을 작성하자
 */
public class Exercise9_4 {
    public static void main(String[] args) {

        TreeNode node = new TreeNode();

        node.insert(3);
        node.insert(4);
        node.insert(1);
        node.insert(2);
        node.insert(5);
        node.insert(6);
        node.insert(7);
        node.insert(8);

        node.printNode();


    }
}
