package org.example.Task4;

import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private final Queue<Integer> data;
    private final int maxSize;

    public Buffer(int maxSize) {
        this.data = new LinkedList<>();
        this.maxSize = maxSize;
    }

    public synchronized void produce(int value) throws InterruptedException {
        while (data.size() == maxSize) {
            wait();
        }
        data.add(value);
        System.out.println("produce: " + value);
        notify(); // Уведомляем ожидающий поток (потребителя)
    }

    public synchronized void consume() throws InterruptedException {
        while (data.isEmpty()) {
            wait();
        }
        int value = data.poll();
        System.out.println("consume: " + value);
        notify(); // Уведомляем ожидающий поток (производителя)
    }
}
