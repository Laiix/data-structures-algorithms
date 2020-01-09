package com.eussi.ch04_stack_queue.util;

// Queue.java
// demonstrates queue
// to run this program: C>java QueueApp
// with no nItems
////////////////////////////////////////////////////////////////
public class Queue2
   {
   private int maxSize;
   private long[] queArray;
   private int front;
   private int rear;
//   private int nItems;
//--------------------------------------------------------------
   public Queue2(int s)          // constructor
      {
      maxSize = s + 1;
      queArray = new long[maxSize];
      front = 0;
      rear = -1;
//      nItems = 0;
      }
//--------------------------------------------------------------
   public void insert(long j)   // put item at rear of queue
      {
      if(rear == maxSize-1)         // deal with wraparound
         rear = -1;
      queArray[++rear] = j;         // increment rear and insert
//      nItems++;                     // one more item
      }
//--------------------------------------------------------------
   public long remove()         // take item from front of queue
      {
      long temp = queArray[front++]; // get value and incr front
      if(front == maxSize)           // deal with wraparound
         front = 0;
//      nItems--;                      // one less item
      return temp;
      }
//--------------------------------------------------------------
   public long peekFront()      // peek at front of queue
      {
      return queArray[front];
      }
//--------------------------------------------------------------
   public boolean isEmpty()    // true if queue is empty
      {
//      return (nItems==0);
        return (rear+1==front || front+maxSize-1==rear);
      }
//--------------------------------------------------------------
   public boolean isFull()     // true if queue is full
      {
//      return (nItems==maxSize);
      return (rear+2==front || front+maxSize-2==rear);
      }
//--------------------------------------------------------------
   public int size()           // number of items in queue
      {
//      return nItems;
        if(rear>front)
           return rear - front + 1;
        else
           return (maxSize-front) + (rear+1);
      }
//--------------------------------------------------------------
   }  // end class Queue
////////////////////////////////////////////////////////////////