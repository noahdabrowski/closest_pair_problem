import java.util.*;
import java.io.*;
import java.awt.*;
//imports

public class Assignment3//class for running the main parts of the program, like selecting the file
{
   public static void main(String [] args)//main method
   {
      try//try statements are good programming :)
      {
         File file = new File(args[0]);//set the file to be the file whose name is in the run args in spot 0
         Scanner inFile = new Scanner(file);//make scanner
      
         int n = getNFromFile(file, inFile);//get the n value to build the list of points(also remove it from the file)
         
         ArrayList<Point> points = new ArrayList<Point>(readFromFile(file, inFile, n));//build the list of points that was given in the file?
         
         if(points.size() >= 2)
         {
            ClosestPair algoCP = new ClosestPair(points);//make the ClosestPair object(this also runs the algorithm in itself)
            //System.out.println("Problem solve time: " + algoCP.getTime() + " nanoseconds");//print out the solve time
            //System.out.print(algoCP.toString());//print the solution
         }
         else
         {
            System.out.println("The number of points must be greater or equal to 2. Algorithm will not run.");
         }
      }
      catch(ArrayIndexOutOfBoundsException AIOBe)//explained by the error message
      {
         System.out.println("You did not provide any run arguments, or you provided them incorrectly.");//neat error message
      }
      catch(FileNotFoundException FNFe)//also explained by error message
      {
         System.out.println("Your file could not be found.");//neato error message
      }
   }
   
   public static int getNFromFile(File file, Scanner inFile)//method to get the n value from the file
   {
      int n = inFile.nextInt();//find the N value that is on the first line of the file
      String garbage = inFile.nextLine();//get rid of the garbage line
      return n;//return n
   }
   
   public static ArrayList<Point> readFromFile(File file, Scanner inFile, int n)//method to read from the given file. Returns an arraylist of points
   {
      //the n value is already gone, and so is the garbage newline that it was on. We can start building the list immediately
      ArrayList<Point> temp = new ArrayList<Point>();//make the temporary arraylist that will store the list of points
      
      while(inFile.hasNextLine())//while the file still has lines to read
      {
         String tempLine = inFile.nextLine();//make a string with the text from the line for easier reading
         Scanner tempScanner = new Scanner(tempLine);//make a scanner to scan the string specifically
         tempScanner.useDelimiter(" ");//they are space separated, so set the delimiter to be a space
         
         int x = tempScanner.nextInt();//get the x value for the current point
         int y = tempScanner.nextInt();//get the y value for the current point
         
         temp.add(new Point(x,y));//add the point to the list
      }
      return temp;//return the list of points
   }
}//end Assignment3 class