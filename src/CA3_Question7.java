import java.util.*;

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
public class CA3_Question7
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
        Map<String, Queue<Block>> sharesMap = new HashMap<>();//stores blocks of shares for each company
        //Queue<Block> sharesQueue = new LinkedList<>();//stores blocks of shares
        double profitTotal = 0;//total from selling shares (Profit)

        String command="";
        do {
            System.out.print(">");
            command = in.next();
            if(command.equalsIgnoreCase("buy"))
            {
                String companyName = in.next();//companys name
                int qty = in.nextInt();//quantity of blocks
                double price = in.nextDouble();//price of blocks (each)
                sharesMap.putIfAbsent(companyName, new LinkedList<>());//if the company exists this does nothing if it doesn't it adds the company to a new linked list
                sharesMap.get(companyName).add(new Block(qty,price));//add shares to que(bought)
            }
            else if(command.equals("sell"))
            {
                String companyName = in.next();
                int qty = in.nextInt();
                double price = in.nextDouble();

                if(sharesMap.containsKey(companyName))
                {
                    //if the company name is present retrieve corresponding shares
                    Queue<Block> sharesQueue = sharesMap.get(companyName);

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
                else
                {
                    System.out.println("No shares are available for Present Company: " + companyName);
                }


            }
        }while(!command.equalsIgnoreCase("quit"));
        System.out.println("Total Profit $"+profitTotal);
    }
}