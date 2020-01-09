package com.eussi.ch06_recursion.util;// StackTriangle2.java
import com.eussi.ch04_stack_queue.util.StackX;

////////////////////////////////////////////////////////////////
public class StackTriangle2
   {
   int theNumber;
   int theAnswer;
   StackX<Integer> theStack;

   public void doTriangle(int theNumber)
      {
      this.theNumber = theNumber;
      stackTriangle();
      System.out.println("Triangle="+theAnswer);
      }  // end main()
//-------------------------------------------------------------
   public void stackTriangle()
      {
      theStack = new StackX<Integer>(10000);    // make a stack

      theAnswer = 0;                   // initialize answer

      while(theNumber > 0)             // until n is 1,
         {
         theStack.push(theNumber);     // push value
         --theNumber;                  // decrement value
         }
      while( !theStack.isEmpty() )     // until stack empty,
         {
         int newN = theStack.pop();    // pop value,
         theAnswer += newN;            // add to answer
         }
      }

   }  // end class StackTriangle2App
////////////////////////////////////////////////////////////////
