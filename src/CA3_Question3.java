import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *  Name: Michal Becmer
 *  Class Group: GD2A
 */

public class CA3_Question3
{
    public static void readFile(String fileName) throws FileNotFoundException {
        try {
            File file = new File(fileName);//open file
            Scanner s = new Scanner(file);//creating a scanner to read file

            // Create a map to store identifiers and their line numbers
            Map<String, StringBuilder> identifierMap = new HashMap<>();

            int lineNum = 0;//initialize the line number

            //read the file line by line
            while (s.hasNextLine()) {
                lineNum++;//increment lineNum
                String line = s.nextLine();//read line and store in variable line
                Scanner l = new Scanner(line);//new scanner to process line
                l.useDelimiter("[^A-Za-z0-9_]+");// Set the delimiter to split the line into tokens, considering only alphanumeric characters and underscores.

                //get identifiers from each line
                while (l.hasNext()) {
                    String indentifier = l.next();

                    //checking if the identifier is not a java keyword
                    if (!javaKeyword(indentifier))//check if it's a java keyword(Keyword list from: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/_keywords.html)
                    {
                        //if its not on the map, add it to the map
                        if (!identifierMap.containsKey(indentifier)) {
                            identifierMap.put(indentifier, new StringBuilder());
                        }
                        //append the lineNum to the StringBuilder associated to the identifier
                        identifierMap.get(indentifier).append(lineNum).append(", ");
                    }
                }
                l.close();//close scanner l
            }
            for (Map.Entry<String, StringBuilder> entry : identifierMap.entrySet())
            {
                // Print the identifier followed by a colon and space, then print the lines where it occurs.
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean javaKeyword(String indentifier)
    {
        //array of all java keywords
        String[] words = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
                        "class", "const", "continue", "default", "do", "double", "else", "enum", "extends",
                        "final", "finally", "float", "for", "if", "goto", "implements", "import", "instanceof",
                        "int", "interface", "long", "native", "new", "package", "private", "protected", "public",
                        "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
                        "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false", "null"};

        //loop through all the words in the array
        for(String word: words)
        {
            //check if it matches a java keyword
            if(indentifier.equals(word))
            {
                return true;//if so return true
            }
        }
        return false;//if not return false
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFile("src/CA3_Question1.java");
    }
}
