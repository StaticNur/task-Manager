package org.example.matrices;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        double[][] matrix1 = generateMatrix();
        double[][] matrix2 = generateMatrix();
        viewMatrix(matrix1);
        System.out.println();
        viewMatrix(matrix2);
        System.out.println();
        double[][] columnsMatrix2 = getColumnMatrix(matrix2);

        Threads[][] thread12 = new Threads[3][3];
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                thread12[i][j] = new Threads(matrix1[i], columnsMatrix2[j]);
                thread12[i][j].start();
            }
        }

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                thread12[i][j].join();
            }
        }
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                System.out.print(thread12[i][j].getResult()+" ");
            }
            System.out.println();
        }
    }
    public static double[][] getColumnMatrix(double[][] matrix){
        double[][] columns = new double[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                columns[i][j] = matrix[j][i];
            }
        }
        return columns;
    }
    public static void viewMatrix(double[][] matrix){
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
    public static double[][] generateMatrix(){
        double[][] matrix = new double[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                matrix[i][j] = i+j;
            }
        }
        return matrix;
    }
}


