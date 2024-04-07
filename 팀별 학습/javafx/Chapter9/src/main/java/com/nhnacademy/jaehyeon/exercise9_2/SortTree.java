package com.nhnacademy.jaehyeon.exercise9_2;

import java.io.PrintWriter;

public class SortTree {

    private Node root;

    public void insert(String value) {
        this.root = insertTree(this.root, value);
    }

    private Node insertTree(Node root, String value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value.compareToIgnoreCase(root.value) < 0) { // 문자열 비교 시 대소문자를 무시하고 비교하는 메서드
            root.left = insertTree(root.left, value);

        } else if (value.compareToIgnoreCase(root.value) > 0) {
            root.right = insertTree(root.right, value);
        }

        return root;
    }

    public void writeTree(PrintWriter writer) {
        writeTreeNode(this.root, writer);
    }

    private void writeTreeNode(Node root, PrintWriter writer) {
        if (root != null) {
            writeTreeNode(root.left, writer);
            writer.println(root.value);
            writeTreeNode(root.right, writer);
        }
    }

    private static class Node {
        String value;
        Node left;
        Node right;

        public Node(String value) {
            this.value = value;
        }
    }

}


