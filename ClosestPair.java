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
      boolean quickSuccess = isSorted(P, true);
      ArrayList<Point> Q = mergesortPointsByY(points, 0, points.size() - 1);
      boolean mergeSuccess = isSorted(Q, false);
      
      if(quickSuccess && mergeSuccess)
      {
         float smallestDist = closestPairSolve(P,Q);
      }
      else
      {
         System.out.println("One or more sorts failed, the rest of the algorithm will not be completed. RIP");
      }
      
      
      long endTime = System.nanoTime();//stop the timer(kinda)
      long totalTimeEstimate = endTime - startTime;//figure out total time
      setTime(totalTimeEstimate);//set the total time
      return totalTimeEstimate;//return the time
   }
   
   public float closestPairSolve(ArrayList<Point> P, ArrayList<Point> Q)
   {
      if(points.size() <= 3)
      {
         //return brute force blah blah
      }
      else
      {
         ArrayList<Point> pLeft = new ArrayList<Point>();
         ArrayList<Point> qLeft = new ArrayList<Point>();
         ArrayList<Point> pRight = new ArrayList<Point>();
         ArrayList<Point> qRight = new ArrayList<Point>();
         
         for(int i = 0; i < P.size() / 2; i++)
         {
            pLeft.add(new Point(P.get(i)));
         }
         for(int i = 0; i < Q.size() / 2; i++)
         {
            qLeft.add(new Point(Q.get(i)));
         }
         
         for(int i = P.size()/2; i < P.size(); i++)
         {
            pRight.add(new Point(P.get(i)));
         }
         for(int i = Q.size()/2; i < Q.size() i++)
         {
            qRight.add(new Point(Q.get(i)));
         }
         
         
         
      }
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
         
         Point tempI = new Point(pointsUnsorted.get(i));
         Point tempJ = new Point(pointsUnsorted.get(j));
         
         pointsUnsorted.set(i,tempJ);
         pointsUnsorted.set(j,tempI);
      }
   }
   
   
   
   public ArrayList<Point> mergesortPointsByY(ArrayList<Point> pointsUnsorted, int l, int r)
   {
      int[] arr = new int[pointsUnsorted.size()];
      
      for(int i = 0; i < pointsUnsorted.size(); i++)
      {
         arr[i] = (int)pointsUnsorted.get(i).getY();
      }
      
      mergesortSort(arr, l, r);
      
      ArrayList<Point> temp = new ArrayList<Point>(pointsUnsorted.size());
      
      for(int i = 0; i < arr.length; i++)
      {
         for(int j = 0; j < pointsUnsorted.size(); j++)
         {
            if(arr[i] == pointsUnsorted.get(j).getY())
            {
               temp.add(i, new Point(pointsUnsorted.get(j)));
            }
         }
      }
      
      return temp;
   }
   public void mergesortSort(int arr[],int l, int r)
   {
      if(l < r)
      {
         int m = (l+r)/2;
         
         mergesortSort(arr, l, m);
         mergesortSort(arr, m+1, r);
         
         mergesortMerge(arr, l, m, r);
      }
   }
   
   public void mergesortMerge(int arr[], int l, int m, int r)
   {
      // Find sizes of two subarrays to be merged
      int n1 = m - l + 1;
      int n2 = r - m;
   
        /* Create temp arrays */
      int L[] = new int [n1];
      int R[] = new int [n2];
   
        /*Copy data to temp arrays*/
      for (int i=0; i<n1; ++i)
         L[i] = arr[l + i];
      for (int j=0; j<n2; ++j)
         R[j] = arr[m + 1+ j];
   
   
        /* Merge the temp arrays */
   
        // Initial indexes of first and second subarrays
      int i = 0, j = 0;
   
        // Initial index of merged subarry array
      int k = l;
      while (i < n1 && j < n2)
      {
         if (L[i] <= R[j])
         {
            arr[k] = L[i];
            i++;
         }
         else
         {
            arr[k] = R[j];
            j++;
         }
         k++;
      }
   
        /* Copy remaining elements of L[] if any */
      while (i < n1)
      {
         arr[k] = L[i];
         i++;
         k++;
      }
   
        /* Copy remaining elements of R[] if any */
      while (j < n2)
      {
         arr[k] = R[j];
         j++;
         k++;
      }
   }
   
   public boolean isSorted(ArrayList<Point> pointsUnsorted, boolean switcher)
   {
      //true = x
      //false = y
      if(switcher)
      {
         int prev = (int)pointsUnsorted.get(0).getX();
         for(int i = 1; i < pointsUnsorted.size(); i++)
         {
            if(prev > pointsUnsorted.get(i).getX())
            {
               //System.out.println("Sort failed when sorting based on X");
               return false;
            }
            prev = (int)pointsUnsorted.get(i).getX();
         }
      }
      else
      {
         int prev = (int)pointsUnsorted.get(0).getY();
         for(int i = 1; i < pointsUnsorted.size(); i++)
         {
            if(prev > pointsUnsorted.get(i).getY())
            {
               //System.out.println("Sorting failed when based on Y");
               return false;
            }
            prev = (int)pointsUnsorted.get(i).getY();
         }
      }
      return true;
   }
   
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