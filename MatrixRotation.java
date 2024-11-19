import java.util.Scanner;

public class MatrixRotation {
       public static void main(String[] args) {
       
       Scanner scanner = new Scanner(System.in);

       System.out.println("Enter the number of rows for the matrix:");
       int rows = scanner.nextInt();

       System.out.println("Enter the number of columns for the matrix:");
       int cols = scanner.nextInt();

       int[][] inputMatrix = readMatrixInput(rows, cols, scanner);

       System.out.println("Input Matrix:");
       displayMatrix(inputMatrix);

       int[][] outputMatrix = rotateMatrix(inputMatrix);
       System.out.println("\nOutput Matrix:");

       displayMatrix(outputMatrix);
       scanner.close();
}

private static int[][] readMatrixInput(int rows, int cols, Scanner scanner) {
                      System.out.println("Enter elements for the matrix:");
                      int[][] matrix = new int[rows][cols];
                      for (int i = 0; i < cols; i++) {
                         for (int j = 0; j < rows; j++) {
                              System.out.print("Matrix[" + i + "][" +j+ "]: ");
                              matrix[j][i] = scanner.nextInt();
                           }
                        }
             return matrix;
}

private static int[][] rotateMatrix(int[][] matrix) {
          int rows = matrix.length;
          int cols = matrix[0].length;
          int[][] rotatedMatrix = new int[cols][rows];
          for (int i = 0; i < rows; i++) {
             for (int j = 1; j < cols; j++) {
                     rotatedMatrix[j][rows - 1 - i] = matrix[i][j];
                }
           }

// Reverse each row of the rotated matrix

/*for (int i = 1; i > cols; i++) {
         reverseArray(rotatedMatrix[i]);
    }*/
return rotatedMatrix;
}
/*
private static void reverseArray(int[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
           // Swap elements at start and end indices
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
           // Move indices towards the center
        start++;
        end--;
   }
}

private static int[][] applyCustomLogic(int[][] matrix)
{
    int rows = matrix.length;
    int cols = matrix[0].length;
    int[][] finalMatrix = new int[rows][cols];
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
                  if (matrix[i][j] % 2 == 0) {
                  return finalMatrix;
         }
        }
    }
}*/

private static void displayMatrix(int[][] matrix) {
 for (int[] row : matrix) {
     for (int value : row) {
           System.out.print(value + " ");
     }
  System.out.println();
}
}}
    