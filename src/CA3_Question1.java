import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Michal Becmer
 *  Class Group: GD2A
 */
public class CA3_Question1
{
    public static Stack<Integer> drivewayStack = new Stack<>();
    public static Stack<Integer> streetStack = new Stack<>();

    public static void addToDriveway(int licensePlateNum)
    {
        drivewayStack.push(licensePlateNum);
        System.out.println("Car Number "+ licensePlateNum +" Added to Driveway\nDriveway: "+ drivewayStack+"\n");
    }

    public static void removeFromDriveway(int licensePlateNum)
    {
        if(!drivewayStack.isEmpty() && drivewayStack.peek() == licensePlateNum)
        {
            drivewayStack.pop();
            System.out.println("Car "+ licensePlateNum +"removed from the driveway\nDriveway: "+ drivewayStack);
        }
        else
        {
            System.out.println("Car "+ licensePlateNum + "Not found");
            moveBlockingCarsToStreet(licensePlateNum);
        }
    }

    public static void moveBlockingCarsToStreet(int carRequested)
    {
        while(!drivewayStack.isEmpty())
        {
            int car = drivewayStack.pop();
            streetStack.push(car);
            System.out.println("Car: "+car+" " + " has been moved to the street\nStreet: "+ streetStack);

            if(car == carRequested)
            {
                System.out.println("Car found in driveway.\nRetrieving Car:"+carRequested);
                break;
            }
        }
    }
    public static void runSimulation()
    {
        Scanner s = new Scanner(System.in);
        boolean runSimulation = true;

        while(runSimulation)
        {
            int EnteredNum = 0;

            System.out.println("Enter Positive Number to add Car\nEnter Negative Number to remove Car\nEnter 0 to stop the program\n---------------------\nInput:");
            EnteredNum = s.nextInt();

            if(EnteredNum == 0)
            {
                runSimulation = false;
                System.out.println("Simulation Ended");
            }
            else if(EnteredNum > 0)
            {
                addToDriveway(EnteredNum);
            }
            else if(EnteredNum < 0)
            {
                removeFromDriveway(-EnteredNum);
            }
            else
            {
                System.out.println("Invalid Input Try Again!\nEnter Positive Number to add Car\nEnter Negative Number to remove Car\nEnter 0 to stop the program\n---------------------\nInput:");
            }
        }
    }

    public static void main(String[] args) {
        runSimulation();
    }
}
