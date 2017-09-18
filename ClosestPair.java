import java.util.*;
import java.io.*;
import java.awt.*;
//imports

public class ClosestPair
{
   ArrayList<Point> points;//arraylist of points given in the file
   
   String smallestDistance;//the smallest distance
   
   ArrayList<Point> closestPoints;//arraylist of points that holds the solution
   
   long time;//this is the operating time of this algorithm
   
   public ClosestPair(ArrayList<Point> p)
   {
      points = p;//give it it's graph
      time = 0;//initial time is 0
      
      time = divideAndConquer();//this runs the algorithm, and also sets the time it took to complete the algorithm
   }
   
   public long divideAndConquer()
   {
      long startTime = System.nanoTime();//start the timer(kinda)
      
      closestPoints = new ArrayList<Point>();//make the list that holds the closest points(the solution)
      
      ArrayList<Point> P = quicksortPointsByX(points, 0, points.size() - 1);
      boolean quickSuccess = isQuickSorted(P);
      //ArrayList<Point> Q = mergesortPointsByY(points, 0, points.size() - 1);
      
      
      
      long endTime = System.nanoTime();//stop the timer(kinda)
      long totalTimeEstimate = endTime - startTime;//figure out total time
      setTime(totalTimeEstimate);//set the total time
      return totalTimeEstimate;//return the time
   }
   
   public ArrayList<Point> quicksortPointsByX(ArrayList<Point> pointsUnsorted, int low, int high)
   {
      if(low >= high)//base case
      {
         return pointsUnsorted;
      }
      
      int pivot = quickPartition(pointsUnsorted, low, high);
      
      quicksortPointsByX(pointsUnsorted, low, pivot);
      
      quicksortPointsByX(pointsUnsorted, pivot + 1, high);
      
      return pointsUnsorted;
   }
   public int quickPartition(ArrayList<Point> pointsUnsorted, int low, int high)
   {
      int pivot = (int)pointsUnsorted.get(low).getX();
      int i = low - 1;
      int j = high + 1;
      
      while(true)
      {
         do
         {
            i++;
         }while(pointsUnsorted.get(i).getX() < pivot);
         
         do
         {
            j--;
         }while(pointsUnsorted.get(j).getX() > pivot);
         
         if(i >= j)
         {
            return j;
         }
         
         Point tempI = pointsUnsorted.get(i);
         Point tempJ = pointsUnsorted.get(j);
         
         pointsUnsorted.set(i,tempJ);
         pointsUnsorted.set(j,tempI);
      }
   }
   public boolean isQuickSorted(ArrayList<Point> pointsUnsorted)
   {
      int prev = (int)pointsUnsorted.get(0).getX();
      for(int i = 1; i < pointsUnsorted.size(); i++)
      {
         if(prev > pointsUnsorted.get(i).getX())
         {
            System.out.println("Quicksort Failed RIP");
            return false;
         }
         prev = (int)pointsUnsorted.get(i).getX();
      }
      
      return true;
   }
   
   //
   
   //public ArrayList<Point> mergesortPointsByY(ArrayList<Point> pointsUnsorted)
   //{
      
      
      
   //}
   
   
   public void setTime(long time)//method to easily set the time after an algorithm run
   {
      time = this.time;//set the time
   }
   public long getTime()//method to easily get the time after an algorithm run
   {
      return time;//get the time
   }
   
   public String toString()//toString method to display the results
   {
      String returnVal = "";//make string
      if(!closestPoints.isEmpty())
      {
         returnVal = "The smallest distance is ";//value to be returned. Will be added on to
         returnVal = returnVal + smallestDistance;//add the smallest distance
         returnVal = returnVal + ", between the following points:\n";//add some more text
         returnVal = returnVal + "(" + closestPoints.get(0).getX() + "," + closestPoints.get(0).getY() + ")\n";//add the first point
         closestPoints.remove(0);
         returnVal = returnVal + "(" + closestPoints.get(0).getX() + "," + closestPoints.get(0).getY() + ")";//add the second point
      }
      else
      {
         returnVal = "The algorithm did not find a solution.";//value to be returned. In this case, it is an error message
      }
      
      return returnVal;//return
   }
}