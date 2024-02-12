import java.util.*;

/**
 * Name: Michal Becmer
 * Class Group: GD2A
 */

/*
Direction enum used to indicate direction.
*/
enum DIRECTION {NORTH, SOUTH, EAST, WEST}

public class CA3_Question9 {

    public static void main(String[] args) {
        //draw out maze for Question
        int[][] maze =
                {
                        {1, 0, 0, 0, 0},
                        {1, 1, 1, 1, 0},
                        {0, 0, 1, 1, 0},
                        {0, 1, 1, 0, 0},
                        {0, 1, 1, 2, 0}
                };

        System.out.println("Maze:");
        display(maze);//shows maze at the beginning

        solve(0, 0, DIRECTION.NORTH, maze);//calls solve method starting at the north position 0,0

        System.out.println("\nCompleted maze:");
        display(maze);//shows maze after completion
    }

    // Display the maze
    public static void display(int[][] maze) {
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[0].length; y++) {
                System.out.printf("%4d", maze[x][y]);
            }
            System.out.println();
        }
    }

    // Solve the maze
    public static void solve(int x, int y, DIRECTION dir, int[][] maze) {
        Stack<int[]> positionStack = new Stack<>(); // Stack to keep track of user position
        positionStack.push(new int[]{x, y}); // Push the user's starting position onto the stack

        // Runs loop until the stack becomes empty or exit is found
        while (!positionStack.isEmpty()) {
            int[] current = positionStack.peek(); // Peek the top element of the stack
            x = current[0];
            y = current[1];

            if (maze[x][y] == 2) {
                System.out.println("Found A Path!");
                return; // Exit the method when the exit is found
            }

            boolean found = false; // For tracking if a path has been found

            //Retrieves an array containing all the values of direction enum
            for (DIRECTION d : DIRECTION.values())
            {
                //sets coords to current positions
                int newX = x;
                int newY = y;

                // Move according to direction
                switch (d) {
                    case NORTH:
                        newX--; //Move north
                        break;
                    case SOUTH:
                        newX++; //Move south
                        break;
                    case EAST:
                        newY++; //Move east
                        break;
                    case WEST:
                        newY--; //Move west
                        break;
                }

                //checks if it's a valid path and if it hasn't been visited
                if (isValidPath(newX, newY, maze) && maze[newX][newY] != 3) {
                    System.out.println("Moving to position: " + newX + ", " + newY);
                    positionStack.push(new int[]{newX, newY});//push onto positionStack to keep track of movement
                    maze[newX][newY] = 3; //Mark the position as visited
                    found = true;//indicates a valid move
                    break;//exits loop to try a new position
                }
            }

            if (!found)//if no valid move has been found
            {
                System.out.println("Backtracking path not found");
                positionStack.pop(); //Backtracks by removing current position from the stack
            }
        }

        System.out.println("Sorry, Couldn't Find A Path"); //If stack empty show
    }



    //check if the position is within bounds and not a wall
    private static boolean isValidPath(int x, int y, int[][] maze) {
        //Check if the position is within the bounds of the maze
        //Checks if its not a wall by checking if its not 0
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != 0;
    }
}
