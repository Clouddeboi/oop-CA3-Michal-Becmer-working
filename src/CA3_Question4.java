import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Michal Becmer
 *  Class Group: GD2A
 */
public class CA3_Question4 {

    /*
        filename: name of the file to test.
     */
    public static boolean validate(String filename) throws FileNotFoundException
    {
        //read file
        File file = new File(filename);
        Scanner s = new Scanner(file);

        //stack for keeping track of opening tags
        Stack<String> stack = new Stack<>();

        //read each line of the file
        while(s.hasNextLine())
        {
            String line = s.nextLine();

            //splits the line into individual tags
            String[] tags = line.split(" ");

            //check each tag in the line
            for(String tag: tags)
            {
                if(tag.startsWith("</"))
                {
                    //if a closing tag is encountered
                    String openingTag = tag.substring(2, tag.length() - 1);//Extract the tag name
                    if(!stack.isEmpty() && stack.peek().equals(openingTag))
                    {
                        stack.pop();// if it matches the top of the stack then pop it
                    }
                    else
                    {
                        return false;//if it doesn't return false
                    }
                }
                else if(tag.startsWith("<"))
                {
                    //if an opening tag is encountered push it onto the stack and then extract the tags name
                    stack.push(tag.substring(1, tag.length() -1));
                }
            }
        }
        return stack.isEmpty();//checks if there are any unclosed tags in the stack
    }

    /*
        This function tests the files in the files array to see if
         they are valid.
         tags_valid.txt should return true;
         tags_invalid.txt should output as invalid;


     */
    public static void main(String[] args) throws FileNotFoundException {
        String[] files = {"src\\tags_invalid.txt", "src\\tags_valid.txt"};
        for(String fName: files) {
            System.out.print(fName +": ");
            if (validate(fName)) {
                System.out.println("This file is valid");
            } else {
                System.out.println("This file is invalid");
            }
        }
    }
}
