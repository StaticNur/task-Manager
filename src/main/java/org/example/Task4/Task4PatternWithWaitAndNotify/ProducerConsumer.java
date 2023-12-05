package org.example.Task4.Task4PatternWithWaitAndNotify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumer {
    private static Queue<Integer> buffer = new LinkedList<>();
    private static final int LIMIT = 10;
    public synchronized void produce() {
        while (buffer.size() >= LIMIT) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Random random = new Random();
        buffer.add(random.nextInt(100));
        System.out.println("Buffer size is after put "+buffer.size());
        notify();
    }
    public synchronized void consumer() {
        while (buffer.size() <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        buffer.poll();
        System.out.println("Buffer size is after take "+buffer.size());
        notify();
    }
}
