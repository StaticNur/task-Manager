package org.example.matrices.executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        double[][] matrix1 = generateMatrix();
        double[][] matrix2 = generateMatrix();
        viewMatrix(matrix1);
        System.out.println();
        viewMatrix(matrix2);
        System.out.println();
        double[][] columnsMatrix2 = getColumnMatrix(matrix2);

        int size = matrix1.length * matrix1.length; //Это количество потоков
        //CountDownLatch countDownLatch = new CountDownLatch(size);
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                executorService.submit(new Processor(matrix1[i], i, columnsMatrix2[j], j));
            }
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        //countDownLatch.await();
        Processor.printResult();
    }

    public static double[][] getColumnMatrix(double[][] matrix) {
        double[][] columns = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                columns[i][j] = matrix[j][i];
            }
        }
        return columns;
    }

    public static void viewMatrix(double[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static double[][] generateMatrix() {
        double[][] matrix = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = i + j;
            }
        }
        return matrix;
    }
}


