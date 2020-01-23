package com.eussi.ch08_binary_tree.util;

/**
 * @author wangxueming
 * @create 2020-01-23 14:04
 * @description
 */
public class ParsePost {
    private StackX theStack;
    private String input;

    // --------------------------------------------------------------
    public ParsePost(String s) {
        input = s;
    }

    // --------------------------------------------------------------
    // 编程作业 8.4
    public CharTree doParse() {
        theStack = new StackX(20);             // make new stack
        char ch;
        int j;
        CharTree num1, num2, interAns;

        for (j = 0; j < input.length(); j++)       // for each char,
        {
            ch = input.charAt(j);              // read from input
            theStack.displayStack("" + ch + " ");  // *diagnostic*
            if (ch >= '0' && ch <= '9') {         // if it's a number
                CharTree temp = new CharTree();
                temp.root = new CharNode(ch);
                theStack.push(temp); // push it
            } else                               // it's an operator
            {
                num2 = theStack.pop();          // pop operands
                num1 = theStack.pop();
                CharTree temp;
                switch (ch)                      // do arithmetic
                {
                    case '+':
                        temp = new CharTree();
                        temp.root = new CharNode('+');
                        temp.root.leftChild = num1.root;
                        temp.root.rightChild = num2.root;
                        theStack.push(temp);
                        break;
                    case '-':
                        temp = new CharTree();
                        temp.root = new CharNode('-');
                        temp.root.leftChild = num1.root;
                        temp.root.rightChild = num2.root;
                        theStack.push(temp);
                        break;
                    case '*':
                        temp = new CharTree();
                        temp.root = new CharNode('*');
                        temp.root.leftChild = num1.root;
                        temp.root.rightChild = num2.root;
                        theStack.push(temp);
                        break;
                    case '/':
                        temp = new CharTree();
                        temp.root = new CharNode('/');
                        temp.root.leftChild = num1.root;
                        temp.root.rightChild = num2.root;
                        theStack.push(temp);
                        break;
                    default:
                        // interAns = 0;
                }  // end switch
                // theStack.push(interAns); // push result
            }  // end else
        }  // end for
        interAns = theStack.pop();            // get answer
        return interAns;
    }  // end doParse()

}
