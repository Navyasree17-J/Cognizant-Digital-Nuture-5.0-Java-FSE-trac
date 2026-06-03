// EXERCISE 17: Matrix Addition
// Objective: Master 2D array declaration, nested loop indexing, and matrix mathematics.

import java.util.Scanner;

public class MatrixAddition17 {
    public static void main(String[] args) {
        // Declaring the Scanner the traditional way for dynamic user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 2D Matrix Addition ===");

        // 1. Prompt the user to define the dimensions of the matrices
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int columns = scanner.nextInt();

        if (rows <= 0 || columns <= 0) {
            System.out.println("Error: Dimensions must be greater than 0.");
            return;
        }

        // 2. Instantiate three 2D arrays (Matrix A, Matrix B, and the Result Matrix)
        int[][] matrixA = new int[rows][columns];
        int[][] matrixB = new int[rows][columns];
        int[][] resultMatrix = new int[rows][columns];

        // 3. Populate Matrix A
        System.out.println("\n--- Enter Elements for Matrix A ---");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Element [" + i + "][" + j + "]: ");
                matrixA[i][j] = scanner.nextInt();
            }
        }

        // 4. Populate Matrix B
        System.out.println("\n--- Enter Elements for Matrix B ---");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Element [" + i + "][" + j + "]: ");
                matrixB[i][j] = scanner.nextInt();
            }
        }

        // 5. Perform Matrix Addition using nested loops
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Add corresponding cells together
                resultMatrix[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        // 6. Display the beautiful finalized math output
        System.out.println("\n=== Final Resulting Matrix ===");
        for (int i = 0; i < rows; i++) {
            System.out.print("[ ");
            for (int j = 0; j < columns; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println("]");
        }
    }
}