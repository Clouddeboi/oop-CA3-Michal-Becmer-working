import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Michal Becmer
 *  Class Group: GD2A
 */

// Represents coordinates in a 2D space
class Cords {
    // Row and column coordinates
    int rowCord;
    int colCord;

    // Constructor to initialize with specified coordinates
    public Cords(int rowCord, int colCord) {
        this.rowCord = rowCord;
        this.colCord = colCord;
    }
}

public class CA3_Question2 {
    /*
        Starter function to create the 2D array and populate it with 0
     */

    public static int[][] floodFillStart() {
        Scanner kb = new Scanner(System.in);
        int[][] arr = new int[10][10];
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                arr[x][y] = 0;
            }
        }
        return arr;
    }

    /*
        Helper function to display the image
     */
    public static void display(int[][] arr) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }

    private static void fill(int r, int c, int[][] arr, Stack<Cords> stack) {
        int order = 1; // Initialize the starting order
        stack.push(new Cords(r, c));

        while (!stack.isEmpty()) {
            // Pop the coordinates from the stack
            Cords current = stack.pop();
            int rowCord = current.rowCord;
            int colCord = current.colCord;

            // If the cell is not filled yet, fill it with the current order and increment the order
            if (arr[rowCord][colCord] == 0) {
                arr[rowCord][colCord] = order++;
            }

            // Check and push unfilled neighbors in the north, east, south, or west direction

            // Check and push the cell to the north if it is within bounds and not filled
            if (rowCord - 1 >= 0 && arr[rowCord - 1][colCord] == 0) {
                stack.push(new Cords(rowCord - 1, colCord));
            }

            // Check and push the cell to the south if it is within bounds and not filled
            if (rowCord + 1 < 10 && arr[rowCord + 1][colCord] == 0) {
                stack.push(new Cords(rowCord + 1, colCord));
            }

            // Check and push the cell to the west if it is within bounds and not filled
            if (colCord - 1 >= 0 && arr[rowCord][colCord - 1] == 0) {
                stack.push(new Cords(rowCord, colCord - 1));
            }

            // Check and push the cell to the east if it is within bounds and not filled
            if (colCord + 1 < 10 && arr[rowCord][colCord + 1] == 0) {
                stack.push(new Cords(rowCord, colCord + 1));
            }
        }
    }

    // Initiates the flood-fill process
    public static void start() {
        // Initialize a 10x10 array with zeros
        int[][] arr = floodFillStart();

        // Prompt user for starting row and column
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter starting row:");
        int startRow = scanner.nextInt();
        System.out.print("Enter starting column:");
        int startCol = scanner.nextInt();

        // Create a stack to store coordinates during flood-fill
        Stack<Cords> stack = new Stack<>();

        // Perform flood-fill starting from the specified coordinates
        fill(startRow, startCol, arr, stack);

        // Display the filled 2D array
        display(arr);
    }

    public static void main(String[] args) {
        start();
    }
}
