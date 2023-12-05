package org.example.Task2;


public class Main {
    public static void main(String[] args) {
        Thread rannableImpl = new Thread(new RunnableImpl());
        rannableImpl.start();
    }
}