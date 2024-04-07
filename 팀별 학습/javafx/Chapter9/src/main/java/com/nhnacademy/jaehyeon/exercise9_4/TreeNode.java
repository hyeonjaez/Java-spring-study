package com.nhnacademy.jaehyeon.exercise9_4;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

    private Node root;

    public void insert(int value) {
        this.root = insertNode(this.root, value);
    }

    public Node insertNode(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        if (root.value > value) {
            root.left = insertNode(root.left, value);
        } else if (root.value < value) {
            root.right = insertNode(root.right, value);
        }
        return root;
    }

    public void printNode() {
        Queue<Node> queue = new LinkedList<>();
        Node current = root;

        while (current != null || !queue.isEmpty()) {
            while (current != null) {
                queue.add(current);
                current = current.left;
            }

            current = queue.poll();
            System.out.println(current.value);

            current = current.right;
        }
    }




    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }


}
