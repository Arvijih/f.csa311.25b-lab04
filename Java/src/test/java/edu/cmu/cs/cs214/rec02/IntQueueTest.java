package edu.cmu.cs.cs214.rec02;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.*;

public class IntQueueTest {
    private IntQueue mQueue;

    @Before
    public void setUp() {
        // ArrayIntQueue-ийг тестлэхэд ашиглана
        mQueue = new ArrayIntQueue();
    }

    @Test
    public void testEnqueueAndDequeue() {
        // Элементийг оруулж, гаргаж авах
        for (int i = 0; i < 10; i++) {
            mQueue.enqueue(i);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(i, (int) mQueue.dequeue());
        }
    }

    @Test
    public void testIsEmpty() {
        // Хоосон эсэхийг шалгах
        assertTrue(mQueue.isEmpty());
        mQueue.enqueue(1);
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testSize() {
        // Queue-ийн хэмжээ
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        assertEquals(2, mQueue.size());
    }

    @Test
    public void testPeek() {
        // Queue-ийн эхний элементийг шалгах
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        assertEquals(1, (int) mQueue.peek());
    }

    @Test
    public void testClear() {
        // Queue-ийг цэвэрлэх
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.clear();
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testContent() throws IOException {
        // Шалгах: бичвэрээс унших
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(result, mQueue.dequeue());
            }
        }
    }

    @Test
    public void testDequeueEmptyQueue() {
        // Хоосон queue-аас элемент авах
        assertNull(mQueue.dequeue());
    }
}
