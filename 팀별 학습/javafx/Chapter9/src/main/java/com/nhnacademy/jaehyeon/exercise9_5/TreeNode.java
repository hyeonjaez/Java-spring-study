package com.nhnacademy.jaehyeon.exercise9_5;

import java.util.HashMap;
import java.util.Map;

public class TreeNode<T extends Comparable<T>> {
    private Node<T> root;

    public void insert(T value) {
        this.root = insertNode(this.root, value);
    }

    private Node<T> insertNode(Node<T> root, T value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        if (root.value.compareTo(value) > 0) {
            root.left = insertNode(root.left, value);
        } else if (root.value.compareTo(value) < 0) {
            root.right = insertNode(root.right, value);
        }
        return root;
    }

    public int getMaxDepth() {
        int max = 0;
        for (int depth : getAllNode().values()) {
            if (max < depth) {
                max = depth;
            }
        }
        return max;
    }

    public int getTotalNode() {
        return getAllNode().size();
    }

    public int calculateTotalNodeDepth() {
        int sum = 0;
        for (int depth : getAllNode().values()) {
            sum += depth;
        }
        return sum;
    }

    private Map<Node<T>, Integer> getAllNode() {
        Map<Node<T>, Integer> nodeDepths = new HashMap<>();
        getAllNodeList(this.root, 0, nodeDepths);
        return nodeDepths;
    }

    private void getAllNodeList(Node<T> node, int depth, Map<Node<T>, Integer> map) {
        if (node == null) {
            return;
        }
        map.put(node, depth);

        getAllNodeList(node.left, depth + 1, map);
        getAllNodeList(node.right, depth + 1, map);
    }

    public double calculateAverageDepth() {
        int totalDepth = calculateTotalNodeDepth();
        int totalNode = getTotalNode();

        return (double) totalDepth / totalNode;
    }

    public void writeTree() {
        writeTreeNode(this.root);
    }

    private void writeTreeNode(Node node) {
        if (node != null) {
            writeTreeNode(node.left);
            System.out.println(node.value);
            writeTreeNode(node.right);
        }
    }

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }
    }
}

