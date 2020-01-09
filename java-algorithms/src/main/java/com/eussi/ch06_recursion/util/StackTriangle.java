package com.eussi.ch06_recursion.util;// StackTriangle.java
////////////////////////////////////////////////////////////////

public class StackTriangle
   {
   static int theNumber;
   static int theAnswer;
   static StackX theStack;
   static int codePart;
   static Params theseParams;
//-------------------------------------------------------------
   public void doTriangle(int theNumber)
      {
      this.theNumber = theNumber;
      recTriangle();
      System.out.println("Triangle="+theAnswer);
      }  // end main()
//-------------------------------------------------------------
   public static void recTriangle()
      {
      theStack = new StackX(10000);
      codePart = 1;
      while( step() == false)  // call step() until it's true
         ;                     // null statement
      }
//-------------------------------------------------------------

      /**
       * public static int triangle1(int n)            //entry codePart 1
       *     {
       *         if(n==1) {                            //codePart 2
       *             return 1;
       *         }
       *         else {
       *             int tmp = triangle1(n-1);         //codePart 3
       *             tmp = n + tmp;                    //codePart 4
       *             return temp;                      //codePart 5 6
       *         }
       *     }
       *
       */
   public static boolean step()
      {
      switch(codePart)
         {
         case 1:                              // initial call
            theseParams = new Params(theNumber, 6);
            theStack.push(theseParams);
            codePart = 2;
            break;
         case 2:                              // method entry
            theseParams = theStack.peek();
            if(theseParams.n == 1)            // test
               {
               theAnswer = 1;
               codePart = 5;   // exit
               }
            else
               codePart = 3;   // recursive call
            break;
         case 3:                              // method call
            Params newParams = new Params(theseParams.n - 1, 4);
            theStack.push(newParams);
            codePart = 2;  // go enter method
            break;
         case 4:                              // calculation
            theseParams = theStack.peek();
            theAnswer = theAnswer + theseParams.n;
            codePart = 5;
            break;
         case 5:                              // method exit
            theseParams = theStack.peek();
            codePart = theseParams.returnAddress; // (4 or 6)
            theStack.pop();
            break;
         case 6:                              // return point
            return true;
         }  // end switch
      return false;
      }  // end triangle
   }  // end class StackTriangleApp
////////////////////////////////////////////////////////////////
