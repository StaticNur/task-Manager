package org.example.Task4;


public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10); // Создаем буфер размером 5

        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}

