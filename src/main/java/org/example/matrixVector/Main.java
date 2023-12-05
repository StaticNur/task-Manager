package org.example.matrixVector;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        double[][] matrix1 = generateMatrix();
        double[] vector = {1,2,3};
        viewMatrix(matrix1);
        System.out.println();
        for (int i =0;i<3;i++)
            System.out.println(vector[i]+" ");
        System.out.println();

        Threads[] thread12 = new Threads[3];
        for(int i=0; i<3; i++) {
            thread12[i] = new Threads(matrix1[i], vector);
            thread12[i].start();
        }
        for(int i=0; i<3; i++) {
            thread12[i].join();
        }

        for(int i=0; i<3; i++) {
            System.out.println(thread12[i].getResult());
        }
    }
    public static void viewMatrix(double[][] matrix){
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
    public static  double[][] generateMatrix(){
        double[][] matrix = new double[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                matrix[i][j] = i+j;
            }
        }
        return matrix;
    }
}


