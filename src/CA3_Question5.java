import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *  Name: Michal Becmer
 *  Class Group: GD2A
 */

public class CA3_Question5
{
    private static Queue<String> takeOffQueue = new LinkedList<>();
    private static Queue<String> landingQueue = new LinkedList<>();

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);

        while(true)
        {
            //instructions
            System.out.println("\nEnter the following Commands to perform the following action");
            System.out.println("takeoff: Takeoff Flight Number");
            System.out.println("land: Land Flight Number");
            System.out.println("next: Call the Next Action");
            System.out.println("quit: Quit Program");
            String userChoice = s.nextLine().toLowerCase();//converts everything to lowercase

            if (userChoice.contains(" ")) {
                // If user input contains a space, split it so that we can extract the command and flight number
                String[] parts = userChoice.split(" ");
                String flightCommand = parts[0];//command part
                String flightNum = parts[1];//flightNum part

                if (flightCommand.equals("takeoff"))
                {
                    takeOff(flightNum); // Calling the takeOff method with the flight number
                }
                else if (flightCommand.equals("land"))
                {
                    landing(flightNum); // Calling the land method with the flight number
                }
                else
                {
                    System.out.println("Invalid command.");//if the user uses an invalid command
                }
            }
            else
            {
                // If user input does not contain a space, use it directly as the command
                if (userChoice.equals("next"))
                {
                    nextAction(); // Calling the nextAction method
                }
                else if (userChoice.equals("quit"))
                {
                    System.out.println("Exiting Program");
                    break;//exits program
                }
                else
                {
                    System.out.println("Invalid command.");//if the user uses an invalid command
                }
            }
        }
    }

    private static void takeOff(String flightNum)//adds flight to takeoff queue
    {
        takeOffQueue.offer(flightNum);//adds flightNum to takeoff queue
        System.out.println("Flight Number " + flightNum +" has been queued for takeoff");
    }

    private static void landing(String flightNum)//adds flight to landing queue
    {
        landingQueue.offer(flightNum);//adds flightNum to takeoff queue
        System.out.println("Flight Number "+ flightNum +" has been queued for landing");
    }

    private static void nextAction()
    {
        if(!landingQueue.isEmpty())//if the landing queue is not empty
        {
            //retrieve and then removes flightNum at the top of the landing queue
            //then prints message
            System.out.println("Landing Flight " + landingQueue.poll() + " in progress");
        }
        else if(!takeOffQueue.isEmpty())//if the landing que is empty and take off isnt
        {
            //retrieves and removes the flightNum at the top of the takeoff queue
            //then prints message
            System.out.println("Flight " + takeOffQueue.poll() +" taking off");
        }
        else//if both queues are empty print message
        {
            System.out.println("There are no flights in the queue");
        }
    }
}
