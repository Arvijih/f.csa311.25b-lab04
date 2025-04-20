package edu.cmu.cs.cs214.rec02;

public class ArrayIntQueue implements IntQueue {
    private int[] elementData;
    private int head;
    private int tail;
    private int size;

    public ArrayIntQueue() {
        elementData = new int[10];
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public void clear() {
        head = 0;
        tail = 0;
        size = 0;
        elementData = new int[10]; // Reset the array
    }

    @Override
    public Integer dequeue() {
        if (isEmpty()) return null;
        Integer value = elementData[head];
        head = (head + 1) % elementData.length;
        size--;
        return value;
    }

    @Override
    public boolean enqueue(Integer value) {
        ensureCapacity();
        elementData[tail] = value;
        tail = (tail + 1) % elementData.length;
        size++;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Integer peek() {
        return isEmpty() ? null : elementData[head];
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = 2 * oldCapacity + 1;
            int[] newData = new int[newCapacity];
            for (int i = head; i < oldCapacity; i++) {
                newData[i - head] = elementData[i];
            }
            for (int i = 0; i < head; i++) {
                newData[oldCapacity - head + i] = elementData[i];
            }
            elementData = newData;
            head = 0;
            tail = size;
        }
    }

    // Main method added here for testing
    public static void main(String[] args) {
        ArrayIntQueue queue = new ArrayIntQueue();

        // Test enqueue
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        // Test peek and dequeue
        System.out.println("Peek: " + queue.peek()); // Should print 1
        System.out.println("Dequeue: " + queue.dequeue()); // Should print 1
        System.out.println("Peek after dequeue: " + queue.peek()); // Should print 2
    }
}
