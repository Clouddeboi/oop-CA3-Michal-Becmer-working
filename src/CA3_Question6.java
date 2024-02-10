import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 *  Name: Michal Becmer
 *  Class Group: GD2A
 */

class Block {
    int quantity;
    double price;

    public Block(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }
}
public class CA3_Question6
{
    /*
    Will repeatedly ask the user to enter the commands in the format
    buy qty price
    or
    sell qty price
    or
    quit
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Queue<Block> sharesQueue = new LinkedList<>();//stores blocks of shares
        double profitTotal = 0;//total from selling shares (Profit)

        String command="";
            do {
            System.out.print(">");
            command = in.next();
            if(command.equalsIgnoreCase("buy"))
            {
                int qty = in.nextInt();
                double price = in.nextDouble();
                sharesQueue.add(new Block(qty,price));//add shares to que(bought)
            }
            else if(command.equals("sell"))
            {
                int qty = in.nextInt();
                double price = in.nextDouble();

                //while qty is over 0 and shares que isnt empty
                //while loop follows FIFO rule
                while (qty > 0 && !sharesQueue.isEmpty())
                {
                    // Retrieve the first block of shares from the queue without removing it
                    Block block = sharesQueue.peek();

                    if(block.quantity <= qty)
                    {
                        // Update the remaining quantity of shares to sell
                        qty -= block.quantity;
                        //calculates new value of profitTotal and removes sold shares from the queue
                        profitTotal += (price - block.price) * block.quantity;
                        sharesQueue.poll();
                    }
                    else
                    {
                        //if the quantity to sell is lower than the quantity of the first block
                        profitTotal += (price - block.price) * qty;
                        //update remaining quantity of first block
                        block.quantity -= qty;
                        qty = 0;//to exit loop we set to 0
                    }
                }
            }
        }while(!command.equalsIgnoreCase("quit"));
        System.out.println("Total Profit $"+profitTotal);
    }
}