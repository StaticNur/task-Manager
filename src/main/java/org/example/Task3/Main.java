package org.example.Task3;

public class Main {
    static volatile int atomicInteger = 0;

    public static void main(String[] args) throws InterruptedException {
        MyThread1 thread1 = new MyThread1();
        MyThread2 thread2 = new MyThread2();
        MyThread3 thread3 = new MyThread3();
        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
    static class MyThread1 extends Thread{
        @Override
        public void run(){
            while (true){
                //increment();
                atomicInteger++;
                System.out.println(atomicInteger);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    static class MyThread2 extends Thread{
        @Override
        public void run(){
            while (true){
                checkDivFive();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    static class MyThread3 extends Thread{
        @Override
        public void run(){
            while (true) {
                checkDivSeven();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    /*private synchronized static void increment(){
        atomicInteger++;
        System.out.println(atomicInteger);
    }*/
    private synchronized static void checkDivFive(){
        if(atomicInteger % 5 == 0){
            System.out.println("Прошло 5: "+atomicInteger);
        }
    }
    private synchronized static void checkDivSeven(){
        if (atomicInteger % 7 == 0) {
            System.out.println("Прошло 7: " + atomicInteger);
        }
    }

}