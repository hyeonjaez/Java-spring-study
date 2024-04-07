package com.nhnacademy.jaehyeon.exercise9_5;

import java.util.Random;


/*
* https://math.hws.edu/javanotes/c9/exercises.html
* 이 연습은 무작위로 1023개의 노드로 구성된 이진 정렬 트리를 생성하고,
* 해당 트리의 모든 리프의 평균 깊이와 최대 깊이를 계산하여 균형 여부를 실험하는 것입니다.
* 트리 내 아이템은 무작위 실수로 생성되며, 세 개의 재귀 서브루틴을 사용하여 리프 수를 세고,
* 리프 깊이 합을 찾으며, 최대 리프 깊이를 확인합니다. 이를 통해 트리의 균형 여부를 확인합니다.
 */
public class Exercise9_5 {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        Random random = new Random();

        for (int i = 0; i < 1023; i++) {
            treeNode.insert(random.nextInt() * 20);
        }

        System.out.println("총 노드의 갯수 : " + treeNode.getTotalNode());
        System.out.println("총 노드의 깊이 : " + treeNode.calculateTotalNodeDepth());
        System.out.println("트리의 총 깊이 : " + treeNode.getMaxDepth());
        System.out.println("노드의 평균 깊이 : " + treeNode.calculateAverageDepth());

    }
}
