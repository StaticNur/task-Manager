package org.example.Task1;

public class ExtensionThread extends Thread {
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 10 == 0) {
                try {
                    System.out.println(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
