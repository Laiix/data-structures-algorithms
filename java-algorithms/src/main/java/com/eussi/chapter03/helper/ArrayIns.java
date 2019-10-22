package com.eussi.chapter03.helper;

// ArrayIns.java
// demonstrates insertion sort
// to run this program: C>java InsertSortApp
//--------------------------------------------------------------
public class ArrayIns
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
//--------------------------------------------------------------
   public ArrayIns(int max)          // constructor
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
   public void insertionSort()
      {
      int in, out;

      for(out=1; out<nElems; out++)     // out is dividing line
         {
         long temp = a[out];            // remove marked item
         in = out;                      // start shifts at out
         while(in>0 && a[in-1] >= temp) // until one is smaller,
            {
            a[in] = a[in-1];            // shift item to right
            --in;                       // go left one position
            }
         a[in] = temp;                  // insert marked item
         }  // end for
      }  // end insertionSort()

      // 编程作业3.2 P78(97) //数组的中间值
      public long median() {
         this.insertionSort(); // 先排序，再取中间值 //这个中间值是大小上的中间值.
         return a[nElems / 2];
      }

      // ==========================================================
      // 编程作业3.5 P79(98)
      public int insertionSort1() {
         int in, out;
         int compare = 0; // 比较次数
         int copy = 0; // 复制次数
         for (out = 1; out < nElems; out++) // out is dividing line
         {
            long temp = a[out]; // remove marked item
            in = out; // start shifts at out
            while (in > 0) // until one is smaller,
            {
               if (a[in - 1] > temp) {
                  a[in] = a[in - 1]; // shift item to right
                  --in; // go left one position
                  compare++;
                  copy++;
               } else {
                  compare++;
                  break;
               }
            }
            a[in] = temp; // insert marked item
         } // end for
         return compare + copy;
      } // end insertionSort()

      // ==========================================================
      // 编程作业3.6 P79(98)
      public void insertionSort2() {
         int in, out, count = 0;
         for (out = 1; out < nElems; out++) // out is dividing line
         {
            long temp = a[out]; // remove marked item
            in = out; // start shifts at out
            while (in > 0 && a[in - 1] >= temp && a[in - 1] != -1) // until one is smaller,
            {
               if (a[in - 1] == temp) {
                  temp = -1;
                  count++;
               }
               a[in] = a[in - 1]; // shift item to right
               --in; // go left one position
            }
            a[in] = temp; // insert marked item
         } // end for
         nElems -= count;
         for (int i = 0; i < nElems; i++) {
            a[i] = a[i + count]; // 把排好序的元素向前移动count个位置
         }
      } // end insertionSort()

      // ===========================================================
      // 编程作业3.3 P78(97)
      // 方法一
      public void noDups() {
         this.insertionSort();// 先排序
         int holenumber = 0;
         final int FLAG = -1; // 标记为空，假设用户不会输入负数
         for (int i = 0; i < nElems; i++) { // 把重复的都标记出来
            for (int j = i + 1; j < nElems; j++) {// 从i+1开始
               if (a[i] == a[j] && a[j] != FLAG) {
                  a[j] = FLAG;
                  holenumber++;
               }
            }
         }

         //下面逻辑与noDups1方法有类似之处，只不过此方法从的第一个order索引从第一个洞开始，之后每遇到一个不是洞的数据，则把他们放到洞的下一个位置
         int firsthole = -1; // 第一个空位的索引
         for (int i = 0; i < nElems; i++) {
            if (a[i] == FLAG && firsthole == -1) { // 遇到第一个空位时,则这个空位为firsthole
               firsthole = i;
            } else if (a[i] != FLAG && firsthole != -1) {// 当有空位时，遇到一个非空值，就把这个非空值复制到firsthole的位置
               a[firsthole++] = a[i]; // 同时，firthole++，空位往后移一位
            }
         }
         nElems -= holenumber;
      }

      // 方法二
      public void noDups1() {
         this.insertionSort();// 先排序
         long NIL = Long.MIN_VALUE; // 标志位
         for (int i = 0; i < nElems - 1; i++) {//只有一个循环，因为已经有序，且是将前一个数据赋值为NIL
            if (a[i] == a[i + 1]) {
               a[i] = NIL; // NIL为标志位，相当于楼主的-1，使用的是Long.MIN_VALUE
            }
         }
         int order = 0;
         for (int temp = 0; temp < nElems; temp++) {
            if (a[temp] != NIL) {// 因为a[0]不可能等于NIL所以才可以用这种方法
               if (temp > order) {
                  a[order] = a[temp];
               }
               order++;
            }
         }
         nElems = order;
      }
   //--------------------------------------------------------------
   }  // end class ArrayIns
////////////////////////////////////////////////////////////////