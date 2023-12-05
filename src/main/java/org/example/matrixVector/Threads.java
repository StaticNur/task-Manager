package org.example.matrixVector;

public class Threads extends Thread {
    private double result;
    private final double[] row;
    private final double[] column;
    public Threads(double[] row, double[] column) {
        this.row = row;
        this.column = column;
    }
    public double getResult()
    {
        return result;
    }
    @Override
    public void run() {
        double matrixValue = 0;
        for (int k = 0; k < 3; k++) {
            matrixValue += row[k] * column[k];
        }
        System.out.println("Работал поток " + Thread.currentThread().getName());
        result = matrixValue;
    }
}
