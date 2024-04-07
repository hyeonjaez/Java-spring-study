package com.nhnacademy.jaehyeon.exercise9_3;

public class LinkedList<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, T data) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("list Index is out bound");
        }

        Node<T> newNode = new Node<>(data);
        if (index == 0) {
            newNode.next = this.head;
            this.head = newNode;
        } else {
            Node<T> preNode = getNode(index - 1);
            newNode.next = preNode.next;
            preNode.next = newNode;
        }
        size++;
    }

    public void add(T data) {
        add(this.size, data);
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("list Index is out bound");
        }

        if (index == 0) {
            this.head = head.next;
        } else {
            Node<T> preNode = getNode(index - 1);
            preNode.next = preNode.next.next;
        }
        size--;
    }

    private Node<T> getNode(int index) {
        Node<T> currentNode = this.head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public void reverse() {
        this.head = reverse(this.head, null);
    }

    private Node<T> reverse(Node<T> current, Node<T> prev) {
        if (current == null) {
            return prev;
        }

        Node<T> next = current.next;
        current.next = prev;
        return reverse(next, current);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = this.head;
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }


    private static class Node<T> {
        T item;
        Node<T> next;

        public Node(T item) {
            this.item = item;
        }
    }
}
