package com.eussi.ch08_binary_tree;

import com.eussi.data._04.StackX;
import com.eussi.data._08.Tree;
import com.eussi.data._08.TreeNode;
import com.eussi.data._08.Huffman;

import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2020-01-23 13:30
 * @description 编程作业
 */
public class Exercise {

    public static void main(String[] args) {
        /**
         *  	8.1	从Tree.java程序出发，把它修改成用用户输入的字母的
         *  		字符串建立二叉树（如A、B等等），每个字母在各自的节点中显示。
         *  		建立树，让每个包含字母的节点是叶节点。父节点可以有非字母标志
         *  		如'+'。保证每个父节点都恰好有两个子节点。不要担心树不平衡。
         *  		注意这不是搜索树；没有快速的方法来查找节点。最后结果可以像下
         *  		面所示的一样：
         *  		                       +
         *                      +                                E
         *         	       +              D              --              --
         *     	      +      C      --      --      --      --      --      --
         *   		A  B  --  --  --  --  --  --  --  --  --  --  --  --  --  --
         *   		一种方法是建立一个树的数组。（一组没有连接的树称为森林。）用
         *   		用户输入的每个字母作为一个节点。把每个节点作为一棵树，节点就
         *   		是根。现在把这些单节点的树放到数组中。下面开始建立一棵以'+'
         *   		为根的树，两个单节点树为它的子节点。依次把数组中的单节点树加
         *   		到这棵大点的树中。不要担心它是不是平衡树。实际可以用生成的中
         *   		间树覆盖数组单元，该数组单元的内容已经加到树中。find()、
         *   		insert()和delete()例程只用于搜索树，可以删掉了。保留
         *   		displayTree()方法和遍历方法，它们对所有二叉树都通用。
         */
        printExercise("8.1");
        exercise_1("abcde");

        /**
         * 8.2扩展编程作业8.1中的功能来创建平衡树。一种方法是保证节点尽可
         * 	能出现在最底层。开始把每对单节点树建成三节点树，以'+'为根。
         * 	这样就有一个三节点树的森林了。合并每对三节点树变成七点节点树
         * 	的森林。随着每棵树节点数目的增加，树的数量减少，直到最后只有
         * 	一棵树。
         */
        sepExercise("8.2");
        exercise_2("abcde");
        println("递归方式：");
        exercise_2_1("abcde");

        /**
         * 8.3	还是从tree.java程序开始。根据用户输入的字符创建树。这次，建
         * 	立完全树--除了底层的最右边可能为空，其它层节点完全满的树。字
         * 	母要从上到下以及从左到右有序，好像就是建立了一个字符的金字塔。
         * 	（这种排列方法与本章前面讨论的三种遍历方法都不对应。）因此，
         * 	字符串ABCDEFGHIJ会排列成下面的样子：
         * 		                       A
         * 	            B                              C
         * 	    D              E              F              G
         * 	H      I      J      --      --      --      --      --
         * 	建立这种树的一种方法是从上到下，而不是像编程作业中前两题那样
         * 	从底向上。从建立一个作为最后树的根的节点开始。如果节点编号和
         * 	字母排顺序一样，1是根，则编号为n的节点的左子节点的编号为2*n+1。
         * 	可以用递归方法，创建两个了节点并在每个子节点中调用它自己的。
         * 	节点创建的顺序不需要和它们插入到树中的顺序相同。像编程作业中
         * 	前面的题那样，可以删掉Tree类中搜索树的一些方法。
         */
        sepExercise("8.3");
        exercise_3("ABCDEFGH");

        /**
         *8.4	编写程序根据后缀表达式建立树，如本章图8.11中所示。需要修改
         *	tree.java程序（清单8.1）中的Tree类，和第4单postfix.java程序
         *	(清单4.8)中的ParsePost类。更多细节参考图8.11的解。建好树之后，
         *	遍历树可以得到算术表达式相应的前缀、中缀和后缀表达式。中缀表达
         *	式需要小括号来避免产生模糊不清的表达式。InOrder()方法中，在第
         *	一次递归调用之前加入左括号，在第二次递归调用之后加入右括号。
         */
        sepExercise("8.4");
        exercise_4("234+*");

        /**
         * 8.5	编写程序实现哈夫曼编码和解码。需要做如下的工作：
         * 	Accept a text message,possibly of more than one line.
         * 	Create a Huffman tree for this message.
         * 	Create a code table.
         * 	Encode the message into binary.
         * 	Decode the message from binary back to text.
         * 	如果信息很短，程序可以显示建立好的哈夫曼树。编程作业8.1、8.2
         * 	和8.3的方法会有所帮助的。可以用String变量把二进制数存储为字
         * 	符1和0的序列。除非确有必要，否则不需要做实际的位处理。
         */
        sepExercise("8.5");
        exercise_5("SUSIE︺SAYS︺IT︺IS︺EASY』");

    }

    public static void exercise_5(String input) {
        println("构建哈夫曼树：");
        Huffman huffman = new Huffman();

        String code = huffman.encode(input);
        println();
        println("Input data: " + input);
        println("encode data: " + code);
        String str = huffman.decode(code);
        println("decode data: " + str);
    }

    public static void exercise_4(String input) {
        println("Enter postfix: " + input);

        if (input.equals(""))       // quit if [Enter]
            return;
        // make a parser
        Tree<Character> output = doParse(input);  // do the evaluation
        // System.out.println("Evaluates to " + output);
        output.displayTree();
        output.traverse(1);
        output.traverse(4);
        output.traverse(3);
    }

    // 编程作业 8.3
    public static void exercise_3(String input) {
        println("输入字符串（至少两个字符）: " + input);
        Tree<Character>[] array = new Tree[input.length()];
        for (int i = 0; i < array.length; i++) {// 建立单节点树数组
            Tree<Character> temp = new Tree<>();
            temp.setRoot(new TreeNode<>(input.charAt(i)));
            array[i] = temp;
        }
        Tree<Character> lastTree = connectTree1(array, 0);
        lastTree.displayTree();
    }
    /**
     * 递归：
     *      从根开始，找他的左右子节点，然后返回根，找左右子节点是，需要判断是否有左右子节点
     * @param array
     * @param index
     * @return
     */
    private static Tree<Character> connectTree1(Tree<Character>[] array, int index) {
        if (index * 2 + 1 > array.length - 1) { // 没有子树
            return array[index];
        } else if (index * 2 + 2 > array.length - 1) { // 有左子树
            Tree<Character> temp = array[index];
            temp.getRoot().leftChild = connectTree1(array, index * 2 + 1).getRoot();      //index*2+1 左子树 index*2+2 右子树
            return temp;
        } else { // 有左右子树
            Tree<Character> temp = array[index];
            temp.getRoot().leftChild = connectTree1(array, index * 2 + 1).getRoot();
            temp.getRoot().rightChild = connectTree1(array, index * 2 + 2).getRoot();
            return temp;
        }
    }

    // 编程作业 8.2
    // 这是按题目8.2要求来的平衡二叉树
    public static void exercise_2(String input) {
        println("输入字符串（至少两个字符）: " + input);
        Tree<Character>[] array = new Tree[input.length()];
        for (int i = 0; i < array.length; i++) {// 建立单节点树数组
            Tree<Character> temp = new Tree<>();
            temp.setRoot(new TreeNode<>(input.charAt(i)));
            array[i] = temp;
        }

        Tree<Character>[] tempArray;
        while (array.length > 1) {
            tempArray = new Tree[(array.length - 1) / 2 + 1]; //计算上一层产生的新树数组，-1保证基数减去了1，偶数也去掉了一个值
            int j = -1;            //新树的数组索引
            int i = 0;
            for (; i + 1 < array.length; i += 2) {      //从零开始，每两个组一棵树，i+1<array.length导致如果有奇数个数，最后一个会剩下
                Tree<Character> temp = new Tree<>();
                temp.setRoot(new TreeNode<>('+'));
                temp.getRoot().leftChild = array[i].getRoot();
                temp.getRoot().rightChild = array[i + 1].getRoot();
                tempArray[++j] = temp;
            }
            if (i < array.length) {         //如果还有一个无法组成一个完成的二叉树，如果array里有奇数个数，会进入此if
                Tree<Character> temp = new Tree<>();
                temp.setRoot(new TreeNode<>('+'));
                temp.getRoot().leftChild = array[array.length - 1].getRoot();
                tempArray[++j] = temp;
            }
            array = tempArray;
        }
        Tree<Character> lastTree = array[array.length - 1];
        lastTree.displayTree();
    }

    // 编程作业 8.2
    public static void exercise_2_1(String input) {
        println("输入字符串（至少两个字符）: " + input);
        Tree<Character>[] array = new Tree[input.length()];
        for (int i = 0; i < array.length; i++) {// 建立单节点树数组
            Tree<Character> temp = new Tree<>();
            temp.setRoot(new TreeNode<>(input.charAt(i)));
            array[i] = temp;
        }

        Tree<Character> lastTree = connectTree(array, 0, array.length - 1);

        lastTree.displayTree();
    }

    /**
     * 递归解法：
     *      因为是平衡数，需要将一定是各一半（如果是奇数，右侧少一个）在根的左右节点上，这样
     *  每次分一半，最终一直到无法再分为止。
     * @param array
     * @param left
     * @param right
     * @return
     */
    private static Tree<Character> connectTree(Tree<Character>[] array, int left, int right) {
        if (left == right) {
            return array[left];
        } else {
            Tree<Character> tempTree = new Tree<>();
            tempTree.setRoot(new TreeNode<>('+'));
            tempTree.getRoot().leftChild = connectTree(array, left, (right + left) / 2).getRoot();
            tempTree.getRoot().rightChild = connectTree(array, (right + left) / 2 + 1, right).getRoot();
            return tempTree;
        }
    }

    // 编程作业 8.1
    public static void exercise_1(String input) {
        println("输入字符串（至少两个字符），实现一: " + input);
        Tree<Character>[] array = new Tree[input.length()];
        for (int i = 0; i < input.length(); i++) {// 建立单节点树数组
            Tree<Character> temp = new Tree<>();
            temp.setRoot(new TreeNode<>(input.charAt(i)));
            array[i] = temp;
        }
        for (int i = 1; i < input.length(); i++) {
            Tree<Character> temp = new Tree<>();
            temp.setRoot(new TreeNode<>('+'));
            temp.getRoot().leftChild = array[i - 1].getRoot();
            temp.getRoot().rightChild = array[i].getRoot();
            array[i] = temp;
        }
        Tree<Character> lastTree = array[input.length() - 1];
        lastTree.displayTree();

        println("输入字符串（至少两个字符），实现二: " + input);
        if(input.length()<=0) {
            println("empty tree ");
        } else {
            Tree<Character> temp = new Tree<>(new TreeNode<>(input.charAt(0)));
            for (int i = 1; i < input.length(); i++) {// 建立单节点树数组
                TreeNode<Character> newNode = new TreeNode<>('+');
                newNode.leftChild = temp.getRoot();
                newNode.rightChild = new TreeNode<>(input.charAt(i));
                temp = new Tree<>(newNode);
            }
            lastTree = temp;
        }
        lastTree.displayTree();
    }

    private static Tree<Character> doParse(String input) {
        StackX<Tree<Character>> theStack = new StackX<>(20);             // make new stack
        char ch;
        int j;
        Tree<Character> num1, num2;

        for (j = 0; j < input.length(); j++) {
            ch = input.charAt(j);              // read from input
            theStack.displayStack("" + ch + " ");  // *diagnostic*
            if (ch >= '0' && ch <= '9') {         // if it's a number
                Tree<Character> temp = new Tree<>();
                temp.setRoot(new TreeNode<>(ch));
                theStack.push(temp); // push it
            } else {
                num2 = theStack.pop();          // pop operands
                num1 = theStack.pop();
                Tree<Character> temp = new Tree<>();
                temp.setRoot(new TreeNode<>(ch));
                temp.getRoot().leftChild = num1.getRoot();
                temp.getRoot().rightChild = num2.getRoot();
                theStack.push(temp);
            }
        }
        return theStack.pop();
    }

}
