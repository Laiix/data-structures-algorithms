package com.eussi.data._06;

import com.eussi.data._04.StackX;

import static com.eussi.util.PrintUtil.println;

public class StackTriangleByStack {
    int theNumber;
    int theAnswer;
    StackX<Integer> theStack;

    public void doTriangle(int theNumber) {
        this.theNumber = theNumber;
        stackTriangle();
        println("Triangle=" + theAnswer);
    }  // end main()

    private void stackTriangle() {
        theStack = new StackX<>(10000);    // make a stack

        theAnswer = 0;                   // initialize answer

        while (theNumber > 0) {
            theStack.push(theNumber);     // push value
            --theNumber;                  // decrement value
        }
        while (!theStack.isEmpty()) {
            int newN = theStack.pop();    // pop value,
            theAnswer += newN;            // add to answer
        }
    }

    public void doTriangle2(int theNumber) {
        this.theNumber = theNumber;
        stackTriangle2();
        println("Triangle=" + theAnswer);
    }  // end main()
    private void stackTriangle2() {
        StackX<Param> theStack = new StackX<>(10000);    // make a stack

        theAnswer = 0;                   // initialize answer
        theStack.push(new Param(theNumber==1, theNumber));

        while (!theStack.isEmpty()) {
            Param popParam = theStack.pop();
            if(popParam.endFlag) {
                theAnswer += popParam.value;
            } else {
                theStack.push(new Param(true, popParam.value));     // push value
                theStack.push(new Param((popParam.value-1)==1, popParam.value-1));     // push value
            }
        }
    }
    private static class Param {
        public boolean endFlag;
        public Integer value;

        public Param(boolean flag, Integer value) {
            this.endFlag = flag;
            this.value = value;
        }


    }

}
