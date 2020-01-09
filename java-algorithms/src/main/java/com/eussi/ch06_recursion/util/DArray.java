package com.eussi.ch06_recursion.util;

// DArray.java
// demonstrates recursive merge sort
////////////////////////////////////////////////////////////////
public class DArray
   {
   private long[] theArray;          // ref to array theArray
   private int nElems;               // number of data items
   //-----------------------------------------------------------
   public DArray(int max)            // constructor
      {
      theArray = new long[max];      // create array
      nElems = 0;
      }
   //-----------------------------------------------------------
   public void insert(long value)    // put element into array
      {
      theArray[nElems] = value;      // insert it
      nElems++;                      // increment size
      }
   //-----------------------------------------------------------
   public void display()             // displays array contents
      {
      for(int j=0; j<nElems; j++)    // for each element,
         System.out.print(theArray[j] + " ");  // display it
      System.out.println("");
      }
   //-----------------------------------------------------------
   public void mergeSort()           // called by main()
      {                              // provides workspace
      long[] workSpace = new long[nElems];
      recMergeSort(workSpace, 0, nElems-1);
      }
   //-----------------------------------------------------------
   private void recMergeSort(long[] workSpace, int lowerBound,
                                               int upperBound)
      {
      if(lowerBound == upperBound)            // if range is 1,
         return;                              // no use sorting
      else
         {                                    // find midpoint
         int mid = (lowerBound+upperBound) / 2;
                                              // sort low half
         recMergeSort(workSpace, lowerBound, mid);
                                              // sort high half
         recMergeSort(workSpace, mid+1, upperBound);
                                              // merge them
         merge(workSpace, lowerBound, mid+1, upperBound);
         }  // end else
      }  // end recMergeSort()
   //-----------------------------------------------------------
   private void merge(long[] workSpace, int lowPtr,
                           int highPtr, int upperBound)
      {
      int j = 0;                             // workspace index
      int lowerBound = lowPtr;
      int mid = highPtr-1;              //左边数组最大索引
      int n = upperBound-lowerBound+1;       // 计算此次排序的数据个数

      while(lowPtr <= mid && highPtr <= upperBound)
         if( theArray[lowPtr] < theArray[highPtr] )
            workSpace[j++] = theArray[lowPtr++];
         else
            workSpace[j++] = theArray[highPtr++];

      while(lowPtr <= mid)
         workSpace[j++] = theArray[lowPtr++];

      while(highPtr <= upperBound)
         workSpace[j++] = theArray[highPtr++];

      //拷贝工作数组上的数据到theArray中
      for(j=0; j<n; j++)
         theArray[lowerBound+j] = workSpace[j];
      }  // end merge()
   //-----------------------------------------------------------
   }  // end class DArray
////////////////////////////////////////////////////////////////

