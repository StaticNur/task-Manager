package org.example.Task4;

class Consumer implements Runnable {
    private final Buffer buffer;
    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        try {
            while (true){
                buffer.consume();
                Thread.sleep(1500); // Имитируем потребление
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
