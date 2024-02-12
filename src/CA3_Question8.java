import java.util.Scanner;
import java.util.Stack;

public class CA3_Question8 {

    public static void main(String[] args) {
        String equation;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter equation");
        equation = in.nextLine().trim();

        Stack<Double> nums = new Stack<>();//stack to hold numbers
        Stack<Character> operators = new Stack<>();//stacks to hold operators

        //store digits
        String num = ""; //empty string

        // Loop through each character in the equation
        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);

            //if char is a digit/decimal
            if (isNumDigit(c))
            {
                num = num + c;
                //if its the last character or the next inputed character is not a digit
                if (i == equation.length() - 1 || (!isNumDigit(equation.charAt(i + 1))))
                {
                    nums.push(Double.parseDouble(num));//converts to double and push into nums stack (digits)
                    num = " "; // Reset num to an empty string
                }
            }
            //if its an opening bracket pushes it onto the operators stack
            else if (c == '(')
            {
                operators.push(c);
            }
            //if its an operator
            else
            {
                while (!operators.isEmpty() && operators.peek() != '('
                        && prec(operators.peek()) >= prec(c)
                        && nums.size() > 1)
                {

                    // Pop two numbers and one operator
                    double operand2 = nums.pop();
                    double operand1 = nums.pop();
                    char operator = operators.pop();

                    // Evaluate the operation and push the result back onto the numbers stack
                    double result = evaluation(operand1, operand2, operator);
                    nums.push(result);
                }

                // Push the current operator onto the operators stack
                operators.push(c);
            }
            if(i == equation.length()-1)//checks if its the last char of equation
            {
                //
                while (!operators.isEmpty() && nums.size() > 1)
                {
                    //pop 2 numbers and 1 operator and then do the operation, finally pushes result to nums stack
                    nums.push(evaluation(nums.pop(), nums.pop(),operators.pop()));
                }
            }
        }
        // Print the final answer
        System.out.println("Answer: " + nums.peek());
    }

    //method for checking if a character is a digit or decimal point( to make my if statement cleaner)
    public static boolean isNumDigit(char c)
    {
        return c >= '0' && c <= '9' || c == '.';
    }

    //evaluates operations
    public static double evaluation(double A, double B, char ops) {
        switch (ops) {
            case '+':
                return A + B;
            case '-':
                return A - B; // Subtract A from B
            case '/':
                return A / B; // Divide A by B
            default:
                return A * B;
        }
    }

    //determines precedence
    public static int prec(char op)
    {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0; // Return 0 for other characters or unrecognized operators
        }
    }
}
