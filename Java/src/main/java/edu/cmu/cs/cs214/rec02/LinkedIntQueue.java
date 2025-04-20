package edu.cmu.cs.cs214.rec02;
public class LinkedIntQueue implements IntQueue {
    private Node front;
    private Node rear;
    private int size;

    private static class Node {
        Integer data;
        Node next;

        Node(Integer data) {
            this.data = data;
        }
    }

    public LinkedIntQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    @Override
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    @Override
    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }
        Integer value = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return value;
    }

    @Override
    public boolean enqueue(Integer value) {
        Node newNode = new Node(value);
        if (rear == null) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return front.data;
    }

    @Override
    public int size() {
        return size;
    }
}
