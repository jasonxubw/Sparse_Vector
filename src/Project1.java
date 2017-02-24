import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

public class Project1
{
    public static void main(String[] args)
    {
        TextFileInput input = new TextFileInput("project1.txt"); //creates new object of TextFileInput
        String line = input.readLine(); //reads in the first line of file
        int count = 1; //keep track of the input line number and allocate the input properly
        String operation = ""; //will store the text of the operation

        SparseVector vector1 = new SparseVector(); //stores the first vector inputted
        SparseVector vector2 = new SparseVector(); //stores the second vector inputted
        SparseVector answer = new SparseVector(); //stores the result after the arithmetic operation is complete
        double productAnswer = 0.0;

        while (line != null) //ends loop when there are are no more lines
        {
            StringTokenizer myTokens = new StringTokenizer(line, " "); //new object of StringTokenizer. "myTokens" will read in the next number separated by a space

            while(myTokens.hasMoreTokens()) //ends loop when there are no more tokens on the line
            {
                if (count % 3 == 1) //each vector operation is inputted in sets of 3 lines -- that's why %3
                {
                    try
                    {
                        vector1.addElements(new Element(Integer.parseInt(myTokens.nextToken()), (Double.parseDouble(myTokens.nextToken()))));
                        //takes in the inputs for the terms and adds them to the vector.
                    }
                    catch (Exception e)
                    {
                        count = count + 2;
                        input.readLine();
                        input.readLine();
                        System.out.println("Illegal input.");
                    }
                }
                else if (count % 3 == 2) //if its the second line of the operation...
                {
                    try
                    {
                        vector2.addElements(new Element(Integer.parseInt(myTokens.nextToken()), Double.parseDouble(myTokens.nextToken())));
                    }
                    catch (Exception e)
                    {
                        count = count + 1;
                        input.readLine();
                        System.out.println("Illegal input.");
                    }
                }
                else //if its the third line of the operation
                {
                    operation = myTokens.nextToken(); //store the operation to the string
                    vector1.sort(); //sorts the vector
                    vector2.sort();
                    if (operation.equals("add")) //if the third line equals to "add", we will add the two vectors
                    {
                        System.out.println("Adding two vectors:");
                        System.out.println(vector1); //prints out the first vector
                        System.out.println("+");
                        System.out.println(vector2);
                        answer = vector1.add(vector2);
                        System.out.println("=");
                        answer.sort();
                        System.out.println(answer);
                        System.out.println("----------------------------------");
                        System.out.println();
                    }
                    else if (operation.equals("subtract")) //if the third line equals to "subtract", we will subtract the two vectors
                    {
                        System.out.println("Subtracting two vectors:");
                        System.out.println(vector1); //prints out the first vector
                        System.out.println("-");
                        System.out.println(vector2);
                        answer = vector1.subtract(vector2);
                        System.out.println("=");
                        answer.sort();
                        System.out.println(answer);
                        System.out.println("----------------------------------");
                        System.out.println();
                    }
                    else if (operation.equals("dot")) //if the third line equals to "multiply", we will multiply the two vectors
                    {
                        System.out.println("Dot product of two vectors:");
                        System.out.println(vector1); //prints out the first vector
                        char dotCharacter = (char) 183;
                        System.out.println(dotCharacter);
                        System.out.println(vector2);
                        productAnswer = vector1.dot(vector2);
                        System.out.println("=");
                        System.out.println(productAnswer);
                        System.out.println("----------------------------------");
                        System.out.println();
                    }
                    vector1 = new SparseVector();
                    vector2 = new SparseVector();
                }
            }
            count ++;
            line = input.readLine();
        }
    }
}

