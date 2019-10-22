package com.eussi.chapter03.helper;


// bubbleSort.java
// demonstrates bubble sort
// to run this program: C>java BubbleSortApp
////////////////////////////////////////////////////////////////
public class ArrayBub
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
//--------------------------------------------------------------
   public ArrayBub(int max)          // constructor
      {
      a = new long[max];                 // create the array
      nElems = 0;                        // no items yet
      }
//--------------------------------------------------------------
   public void insert(long value)    // put element into array
      {
      a[nElems] = value;             // insert it
      nElems++;                      // increment size
      }
//--------------------------------------------------------------
   public void display()             // displays array contents
      {
      for(int j=0; j<nElems; j++)       // for each element,
         System.out.print(a[j] + " ");  // display it
      System.out.println("");
      }
//--------------------------------------------------------------
   public void bubbleSort()
      {
      int out, in;

      for(out = nElems - 1; out > 0; out--)   // outer loop (backward) 刚开始要比较到倒数第二个数据,注意最后结束的条件，一致减到0
         for(in=0; in<out; in++)        // inner loop (forward)
            if( a[in] > a[in+1] )       // out of order?
               swap(in, in+1);          // swap them
      }  // end bubbleSort()

      //编程作业3.1
      public void bubbleSort1()
      {
         int outLeft = 0;
         int outRight = nElems - 1;
         int in = 0;
         int dest = 0;

         while(outLeft<outRight) {
            //考虑方向
            if(dest==0) {//正向
               if (in == outRight) {
                  dest = 1;//调整方向
                  in = outRight --;
               } else {
                  if (a[in] > a[in + 1]) {
                     swap(in, in + 1);
                  }
                  in ++;
               }
            } else {
               if(in == outLeft) {
                  dest = 0;
                  in = outLeft ++;
               } else {
                  if(a[in-1] > a[in]) {
                     swap(in-1, in);
                  }
                  in --;
               }
            }
         }

      }  // end bubbleSort()

      public void bubbleSort2() {
         int leftout = 0, rightout = nElems - 1, in; // leftout,rightout为左右两端指针

         for (; rightout > leftout; rightout--, leftout++) {
            for (in = leftout; in < rightout; in++)
               if (a[in] > a[in + 1])
                  swap(in, in + 1);
            for (in = rightout-1; in > leftout; in--)//注意，in=rightout-1,因为rightout已经是最大了
               if (a[in] < a[in - 1])
                  swap(in, in - 1);
         }
      }

//--------------------------------------------------------------
   private void swap(int one, int two)
      {
      long temp = a[one];
      a[one] = a[two];
      a[two] = temp;
      }
//--------------------------------------------------------------
   }  // end class ArrayBub
////////////////////////////////////////////////////////////////