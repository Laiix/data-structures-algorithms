package com.eussi.ch07_advanced_sort.util;

import java.util.ArrayList;

// ArrayPar.java
// demonstrates partitioning an array
////////////////////////////////////////////////////////////////
public class ArrayPar
   {
   private long[] theArray;          // ref to array theArray
   private int nElems;               // number of data items
//--------------------------------------------------------------
   public ArrayPar(int max)          // constructor
      {
      theArray = new long[max];      // create the array
      nElems = 0;                    // no items yet
      }
//--------------------------------------------------------------
   public void insert(long value)    // put element into array
      {
      theArray[nElems] = value;      // insert it
      nElems++;                      // increment size
      }
//--------------------------------------------------------------
    public long get(int index)    // put element into array
    {
        return theArray[index];
    }
       //--------------------------------------------------------------
   public int size()                 // return number of items
      { return nElems; }
//--------------------------------------------------------------
   public void display()             // displays array contents
      {
      System.out.print("A=");
      for(int j=0; j<nElems; j++)    // for each element,
         System.out.print(theArray[j] + " ");  // display it
      System.out.println("");
      }
//--------------------------------------------------------------
    public int partitionIt(int left, int right, long pivot)
       {
       int leftPtr = left - 1;           // right of first elem
       int rightPtr = right + 1;         // left of pivot
       while(true)
          {
          while(leftPtr < right &&       // find bigger item
                theArray[++leftPtr] < pivot)
             ;  // (nop)

          while(rightPtr > left &&       // find smaller item
                theArray[--rightPtr] > pivot)
             ;  // (nop)
          if(leftPtr >= rightPtr)        // if pointers cross,
             break;                      //    partition done
          else                           // not crossed, so
             swap(leftPtr, rightPtr);    //    swap elements
          }  // end while(true)
       return leftPtr;                   // return partition
       }  // end partitionIt()
//--------------------------------------------------------------

       // =============================================================
       // 编程作业 7.1
       public int partitionIt(int left, int right) {
           int leftPtr = left - 1;           // right of first elem
           int rightPtr = right;         	  // left of pivot
           long pivot = theArray[right];
           while (true) {
               while (theArray[++leftPtr] < pivot)
                   ;  // (nop)

               while (rightPtr > left &&       // find smaller item
                       theArray[--rightPtr] > pivot)
                   ;  // (nop)
               if (leftPtr >= rightPtr)        // if pointers cross,
                   break;                      // partition done
               else
                   // not crossed, so
                   swap(leftPtr, rightPtr);    // swap elements
           }  // end while(true)
           swap(leftPtr, right);
           return leftPtr;                   // return partition
       }  // end partitionIt()


       // =============================================================
       // 编程作业 7.3
       public int findMedian(int left, int right) {
           int leftPtr = left - 1;           // right of first elem
           int rightPtr = right;         	  // left of pivot
           long pivot = theArray[right];
           while (true) {
               while (theArray[++leftPtr] < pivot)
                   ;  // (nop)

               while (rightPtr > left &&       // find smaller item
                       theArray[--rightPtr] > pivot)
                   ;  // (nop)
               if (leftPtr >= rightPtr)        // if pointers cross,
                   break;                      // partition done
               else
                   // not crossed, so
                   swap(leftPtr, rightPtr);    // swap elements
           }  // end while(true)
           swap(leftPtr, right);

           int midindex = theArray.length / 2; // 中间位置

           if (leftPtr == midindex) {
               return leftPtr;
           } else if (leftPtr > midindex) {
               return findMedian(left, leftPtr - 1);
           } else {
               return findMedian(leftPtr + 1, right);
           }

       }

       // =============================================================
       // 编程作业 7.3
       public long median() {
           return theArray[findMedian(0, theArray.length - 1)];
       }

       public void insertionSort()
       {
           int in, out;

           for(out=1; out<nElems; out++)     // out is dividing line
           {
               long temp = theArray[out];            // remove marked item
               in = out;                      // start shifts at out
               while(in>0 && theArray[in-1] >= temp) // until one is smaller,
               {
                   theArray[in] = theArray[in-1];            // shift item to right
                   --in;                       // go left one position
               }
               theArray[in] = temp;                  // insert marked item
           }  // end for
       }  // end insertionSort()


       // 编程作业 7.4
       public int findIndex(int left, int right, int index) {
           int leftPtr = left - 1;           // right of first elem
           int rightPtr = right;         	  // left of pivot
           long pivot = theArray[right];
           while (true) {
               while (leftPtr < right &&       // find bigger item
                       theArray[++leftPtr] < pivot)
                   ;  // (nop)

               while (rightPtr > left &&       // find smaller item
                       theArray[--rightPtr] > pivot)
                   ;  // (nop)
               if (leftPtr >= rightPtr)        // if pointers cross,
                   break;                      // partition done
               else
                   // not crossed, so
                   swap(leftPtr, rightPtr);    // swap elements
           }  // end while(true)
           swap(leftPtr, right);

           if (leftPtr == index) {
               return leftPtr;
           } else if (leftPtr > index) {
               return findIndex(left, leftPtr - 1, index);
           } else {
               return findIndex(leftPtr + 1, right, index);
           }
       }

       public long findKth(int k) {
           if (k < 1 || k > theArray.length) {
               return -1;
           }
           return theArray[findIndex(0, theArray.length - 1, k - 1)];
       }


   public void swap(int dex1, int dex2)  // swap two elements
      {
      long temp;
      temp = theArray[dex1];             // A into temp
      theArray[dex1] = theArray[dex2];   // B into A
      theArray[dex2] = temp;             // temp into B
      }  // end swap()
//--------------------------------------------------------------
   }  // end class ArrayPar
