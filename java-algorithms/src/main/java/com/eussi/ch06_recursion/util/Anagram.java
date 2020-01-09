package com.eussi.ch06_recursion.util;// anagram.java
// creates anagrams
// to run this program: C>java AnagramApp

////////////////////////////////////////////////////////////////
public class Anagram
   {
   static int size;
   static int count;
   static char[] arrChar;

   public Anagram(String s) {
      size = s.length();
      arrChar = s.toCharArray();
   }
   //-----------------------------------------------------------
   public void doExecute()
      {
      doAnagram(size);                       // anagram it
      }  // end main()
   //-----------------------------------------------------------
   public void doAnagram(int newSize)
      {
      if(newSize == 1)                     // if too small,
         return;                           // go no further

      for(int j=0; j<newSize; j++)         // for each position,
         {
         doAnagram(newSize-1);             // anagram remaining
         if(newSize==2)                    // if innermost,
            displayWord();                 // display it
         rotate(newSize);                  // rotate word
         }
      }
   //-----------------------------------------------------------
   // rotate left all chars from position to end
   public void rotate(int newSize)
      {
      int j;
      int position = size - newSize;
      char temp = arrChar[position];       // save first letter
      for(j=position+1; j<size; j++)       // shift others left
         arrChar[j-1] = arrChar[j];
      arrChar[j-1] = temp;                 // put first on right
      }
   //-----------------------------------------------------------
   public void displayWord()
      {
      if(count < 99)
         System.out.print(" ");
      if(count < 9)
         System.out.print(" ");
      System.out.print(++count + " ");
      for(int j=0; j<size; j++)
         System.out.print( arrChar[j] );
      System.out.print("   ");
      System.out.flush();
      if(count%6 == 0)
         System.out.println("");
      }

   }
////////////////////////////////////////////////////////////////
