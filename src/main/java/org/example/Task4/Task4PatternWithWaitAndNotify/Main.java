package org.example.Task4.Task4PatternWithWaitAndNotify;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();
        Thread thread1 = new Thread(() -> {
            while (true){
                pc.produce();
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true){
                pc.consumer();
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
