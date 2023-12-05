package org.example.matrices.executor;

import java.util.concurrent.CountDownLatch;

public class Processor extends Thread {
    private static double[][] result = new double[3][3];
    private final int i;
    private final int j;
    //private final CountDownLatch countDownLatch;
    private final double[] row;
    private final double[] column;

    public Processor(double[] row,int i, double[] column,int j) {
        this.row = row;
        this.i = i;
        this.column = column;
        this.j = j;
        //this.countDownLatch = size;
    }

    public static void printResult() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void run() {
        double matrixValue = 0;
        for (int k = 0; k < 3; k++) {
            matrixValue += row[k] * column[k];
        }
        System.out.println("Работал поток " + Thread.currentThread().getName());
        Processor.result[i][j] = matrixValue;
        //countDownLatch.countDown();
    }
}
