package com.eussi.data._06;// anagram.java

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;

public class Anagram {
    static int size;
    static int count;
    static char[] arrChar;

    public Anagram(String s) {
        size = s.length();
        arrChar = s.toCharArray();
    }

    public void doExecute() {
        doAnagram(size);                       // anagram it
    }  // end main()

    public void doAnagram(int newSize) {
        if (newSize == 1)                     // if too small,
            return;                           // go no further

        for (int j = 0; j < newSize; j++) {
            doAnagram(newSize - 1);             // anagram remaining
            if (newSize == 2)                    // if innermost,
                displayWord();                 // display it
            rotate(newSize);                  // rotate word
        }
    }

    // rotate left all chars from position to end
    public void rotate(int newSize) {
        int j;
        int position = size - newSize;
        char temp = arrChar[position];       // save first letter
        for (j = position + 1; j < size; j++)       // shift others left
            arrChar[j - 1] = arrChar[j];
        arrChar[j - 1] = temp;                 // put first on right
    }

    public void displayWord() {
        if (count < 99)
            print(" ");
        if (count < 9)
            print(" ");
        print(++count + " ");
        for (int j = 0; j < size; j++)
            print(arrChar[j]);
        print("   ");
        System.out.flush();
        if (count % 6 == 0)
            println("");
    }

}
