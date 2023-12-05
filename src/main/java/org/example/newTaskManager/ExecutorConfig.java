package org.example.newTaskManager;

public class ExecutorConfig {
    private int numThreads;
    public ExecutorConfig(int numThreads) {
        this.numThreads = numThreads;
    }
    public int getNumThreads() {
        return numThreads;
    }
    public void setNumThreads(int numThreads) {
        this.numThreads = numThreads;
    }
}
