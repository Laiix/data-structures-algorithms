package com.eussi.ch04_stack_queue;

import com.eussi.data._04.*;

import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2019-10-25 10:20
 * @description
 */
public class Stack$Queue {
    public static void main(String[] args) {
        /**
         * 不同的结构类型
         *      本章所讲的数据结构和算法与前面章节提到的有很大不同。在开始详细讲解新的结构之前,先
         * 来看看其中的三个区别:
         * 1-程序员的工具
         *      数组是前面已经介绍过的数据存储结构,和后面将遇到的其他结构(链表、树等等)一样
         * 都适用于数据库应用中作数据记录。它常用于记录那些对应于现实世界的对象和活动的数据,如职
         * 员档案、目录和商务数据等等。这些结构便于数据的访问:它们易于进行插入、删除和查找特定数
         * 据项的操作。
         *      然而,本章要讲解的数据结构和算法更多的是作为程序员的工具来运用。它们主要作为构思算
         * 法的辅助工具,而不是完全的数据存储工具。这些数据结构的生命周期比那些数据库类型的结构要
         * 短得多。在程序操作执行期间它们才被创建,通常用它们去执行某项特殊的任务;当完成任务之后,
         * 它们就被销毁。
         * 2-受限访问
         *      在数组中,若知道数据项的下标,便可以立即访问该数据项;或者通过顺序搜索数据项,访问
         * 到数组中的各数据项。而在本章的数据结构中,访问是受限制的,即在特定时刻只有一个数据项可
         * 以被读取或者被删除(除非“作弊”)
         *      这些结构接口的设计增强了这种受限访问。访问其他数据项(理论上)是不允许的。
         * 3-更加抽象
         *      栈、队列和优先级队列是比数组和其他数据存储结构更为抽象的结构。主要通过接口对栈、队
         * 列和优先级队列进行定义,这些接口表明通过它们可以完成的操作,而它们的主要实现机制对用户来说
         * 是不可见的
         *      例如，栈可以通过数组或链表实现；优先级队列可以用数组或堆来实现
         */

        /**
         * 栈
         *      栈只允许访问一个数据项:即最后插入的数据项。移除这个数据项后才能访问倒数第二个插入
         * 的数据项,依此类推。这种机制在不少编程环境中都很有用。接下来将看到如何利用栈来检验源程
         * 序中的小括号、中括号和大括号是否匹配的问题。本章的最后会讲到栈在解析算术表达式时起到的
         * 极为重要的作用,比如解析3*(4+5)。
         *      栈也是那些应用了相当复杂的数据结构算法的便利工具。比如在第8章“二叉树”中,用栈来
         * 辅助遍历树的节点;第13章“图”中,利用栈来辅助查找图的顶点(一种可以用来解决迷宫问题
         * 的技术)。
         *      大部分微处理器运用基于栈的体系结构。当调用一个方法时,把它的返回地址和参数压入栈,
         * 当方法结束返回时,那些数据出栈。栈操作就嵌入在微处理器中
         *      一些比较老的便携式计算器也用基于栈的体系结构。它们不是输入带括号的算术表达式,而是
         * 把中间结果先存入栈中。本章最后讨论解析算术表达式问题时将更详细地讲述这种方法
         * 栈的基本操作：
         *      POP(出栈)
         *      PUSH(入栈)
         *      PEEK(查看)
         * 栈的容量：
         *      栈通常很小，是临时数据结构。当然，实际程序中的栈的容量会更大一些。但奇怪的是栈容量的需求
         * 是那么小，例如，解析一个很长的算数表达式只需要十几个单元的栈即可
         */
        testStackX();
        sep();
        /**
         * 栈的应用：
         * 1-单词逆序输入，入栈再出栈即可实现
         * 2-分隔符匹配：
         */
        testBracketChecker();
        sep();
        /**
         * 栈是一个概念上的辅助工具
         *      由上可见,匹配分隔符使用栈是多么方便。同样也可以利用普通数组来完成栈的操
         * 作,但是那样就不得不自己老惦记着最后添加的字符的下标值,和某些记账的问题一样。栈在抽象
         * 概念上更便于使用。栈通过提供限定性的访问方法 push和pop,使程序易读且不易出错。(拿木
         * 匠的话来说,用正确的工具干活更安全。)
         * 栈的效率
         *      Stack类中实现的栈,数据项入栈和出栈的时间复杂度都为常数o(1)。这也就是说,栈操作所
         * 耗的时间不依赖于栈中数据项的个数,因此操作时间很短。栈不需要比较和移动操作
         */


        /**
         * 队列
         *      “队列”(queue)这个单词是英国人说的“排”(line)(一种等待服务的方式)。在英国,“排队”
         * 的意思就是站到一排当中去。计算机科学中,队列是一种数据结构,有点类似栈,只是在队列中第
         * 个插入的数据项也会最先被移除(先进先出,FIFO),而在栈中,最后插入的数据项最先移除
         * (LIFO)。队列的作用就像电影院前的人们站成的排一样:第一个进入队尾的人将最先到达队头买
         * 票。最后排队的人最后才能买到票。
         *      队列和栈一样也被用作程序员的工具。
         *      在计算机(或网络)操作系统里,有各种队列在安静地工作着。打印作业在打印队列中等待打
         * 印。当在键盘上敲击时,也有一个存储键入内容的队列。同样,如果使用文字处理程序敲击一个键
         * 而计算机又暂时要做其他的事,敲击的内容不会丢失,它会排在队列中等待,直到文字处理程序有
         * 时间来读取它。利用队列保证了键入内容在处理时其顺序不会改变。
         *
         * 循环队列：
         *      在队列中，当插入一个新的数据项，队头的指针(REAR)向后移动，移向下标大的位置；移除
         * 数据时，队尾的指针(FRONT)也是向后移动，移向下标大的位置。这种设计可能和人们直观感觉相
         * 反,因为人们在买电影票排队时,队伍总是向前移动的,当前面的人买完票离开队伍后,其他人都向前
         * 移动。计算机中在队列里删除一个数据项后,也可以将其他数据项都向前移动,但这样做的效率很差。
         * 相反,我们通过队列中队头和队尾指针的移动保持所有数据项的位置不变
         *      这样设计的问题是队尾指针很快就会移到数组的末端(数组下标最大)。虽然在数组的开始部
         * 分有空的数据项单元,这是移除后移除的数据项的位置,但是由于因为队尾指针不能再向后移动了,
         * 因此也不能再插入新的数据项。
         *
         * 环绕式处理
         *      为了避免队列不满却不能插入新数据项的问题,可以让队头队尾指针绕回到数组开始的位置
         * 这就是循环队列(有时也称为“缓冲环”)
         *      在队尾指针回绕之后,它现在处在队头指针的下面,这就颠倒了初始的位置。这可以称为“折
         * 断的序列”:队列中的数据项存在数组两个不同的序列中
         *      删除足够多的数据项后,队头指针也回绕。这时队列的指针回到了初始运行时的位置状态,队
         * 头指针在队尾指针的下面。数据项也恢复为单一的连续的序列。
         */
        testQueueWithCount();
        sep();
        /**
         * 没有数据项计数字段的队列实现
         *      在 Queue类中包含数据项计数字段 nItems会使 insert()和 remove()方法增加一点额外的操作
         * 因为 insert()和 remove()方法必须分别递增和递减这个变量值。这可能算不上额外的开销,但是如果
         * 处理大量的插入和移除操作,这就可能会影响性能了。
         *      因此,一些队列的实现不使用数据项计数的字段,而是通过 front和rear来计算出队列是否空
         * 或者满以及数据项的个数。如果这样做, isEmpty()、isFull()和size()例程会相当复杂,因为就像前
         * 面讲过的那样,数据项的序列或者被折成两段,或者是连续的一段
         *      而且,一个奇怪的问题出现了。当队列满的时候, front和rear指针取一定的位置,但是当队列
         * 为空时,也可能呈现相同的位置关系。于是在同一时间,队列似乎可能是满的,也可能是空的。
         *      这个问题可以这样解决,让数组容量比队列数据项个数的最大值还要大一。
         */
        testQueue();
        sep();
        /**
         * 队列的效率
         *      和栈一样，队列中插入数据项和移除数据项的时间复杂度均为O(1)
         *
         * 双端队列
         *      双端队列就是一个两端都是结尾的队列。队列的每一端都可以插入数据项和移除数据项。这些
         * 方法可以叫作 insertLeft()和 insertRight(),以及 removeLeft()和 removeRight()。
         *      如果严格禁止调用 insertLeft()和 removeLeft()方法(或禁用右端的操作),双端队列功能就和栈
         * 样。禁止调用 insertleft()和 moveright()方法(或相反的另一对方法),它的功能就和队列一样了
         *      双端队列与栈或队列相比，是一种多用途的数据结构，在容器类库中有时会用双端队列来提供栈和队
         * 列中的两种功能。但是，双端队列不像栈和队列那么常用， 因此这次不再详述。
         */

        /**
         * 优先级队列
         *      优先级队列是比栈和队列更专用的数据结构。但它在很多的情况下都很有用。像普通队列一样
         * 优先级队列有一个队头和一个队尾,并且也是从队头移除数据项。不过在优先级队列中,数据项按
         * 关键字的值有序,这样关键字最小的数据项(或者在某些实现中是关键字最大的数据项)总是在队
         * 头。数据项插入的时候会按照顺序插入到合适的位置以确保队列的顺序。
         *      像栈和队列一样,优先级队列也经常用作程序员编程的工具。后面图的算法中,可以看到在图的最
         * 小生成树算法中应用优先级队列
         *      像普通的队列一样,优先级队列在某些计算机系统中也有很多应用。例如,在抢先式多仼务橾
         * 作系统中,程序排列在优先级队列中,这样优先级最高的程序就会先得到时间片并得以运行。
         *      很多情况下需要访问具有最小关键字值的数据项(比如要寻找最便宜的方法或最短的路径去做
         * 某件事)。因此,具有最小关键字值的数据项具有最高的优先级。这里略微有点专断,假定所有情
         * 况都是这样的,尽管有很多情况都是最大关键字具有最高的优先级。
         *      除了可以快速访问最小关键值的数据项,优先级队列还应该可以实现相当快的插入数据项。因
         * 此,正如前面提到过的,优先级队列通常使用一种称为堆的数据结构来实现。第12章将会介绍堆
         * 本章使用简单的数组实现优先级队列。这种实现方法插入比较慢,但是它比较简单,适用于数据量
         * 比较小并且不是特别注重插入速度的情况。
         */
        testPriorityQ();
        sep();

        /**
         * 解析算数表达式应用：
         *      对计算机的算法来说如果直接求算数表达式的值还是相当困难的。因此，分两步实现算法比较容易：
         *      1、将算数表达式转换为成另一种形式：后缀表达式
         *      2、计算后缀表达式的值
         *      第一个步骤比较麻烦，第二部简单，但是不管怎么说，这种分两步的算法比直接解析算数表达式的
         * 方法容易。
         * 后缀表达式：
         *      后缀表达式也称作波兰逆序表达式（Reverse Polish Notation），或者RPN，它是由一位波兰的
         * 数学家发明的，操作符跟在两个操作数的后面。如：
         *      A+B                     AB+
         *      A+B*C                   ABC*+
         *      ((A+B)*C)-D             AB+C*D-
         *      A+B*(C-D/(E+F))         ABCDEF+/-*+
         *      有些计算机语言也用一个操作符标识乘方（通常，用"^"符号），这里暂不讨论这种表示
         * 除了中缀和后缀表达式，还有一种前缀表达式，操作符写在两个操作数的前面，如A+B为+AB。这
         * 中表达方法与后缀表达式功能类似，但是很少使用。
         *
         * 中缀表达式转后缀表达式
         *      计算中缀表达式时，既要向前，又要向后读表达式。向前（从左到右）读操作数和操作符，
         * 等到读到足够的信息来执行一个运算时，向后去找出两个操作数和一个操作符，执行运算。
         *      有时后面如果是更高级别的操作符或者括号时，就必须推迟此运算。这种情况下，必须先
         * 执行后面级别高的运算，然后再回头执行前面的运算。
         *      我们可以直接编写这样求值的算法，但是，先把表达式转换成后缀表达式计算会更容易。
         *      通过栈来存储操作符是一个很好的方法。操作符在中缀表达式中的初始顺序与后缀表达式中
         * 的顺序有些颠倒的感觉，这与栈的FILO有些相似之处。
         *      从某方面说，由栈中弹出数据项实际上能从右向左扫描输入字符串。但是因为在读输入串的
         * 时候已经被压入栈中，所以只需要出栈即可实现逆序重调用他们。
         *      操作数在中缀表达式中出现的顺序是相同的，因此可以读到操作数的时候就输出他们，它们
         * 不需要存储在栈里
         * 算法概述：
         *      扫描表达式，如果遇到：
         *          操作数：
         *              直接写到输出
         *          左括号（：
         *              入栈
         *          右括号）：
         *              栈非空循环
         *                  弹出一项
         *                  是（退出循环，否则写至输出
         *          操作符：
         *              栈不为空循环
         *                  弹出一项
         *                  若是（
         *                      （入栈，退出循环
         *                  否则
         *                      如果出栈操作符优先级小于当前读入，出栈数据入栈，并且退出循环
         *                      如果出栈操作符优先级大于等于当前读入，输出出栈数据
         *              当前操作符入栈
         *           结束：
         *              栈非空时，弹出所有数据，写至输出
         */
        testInfix();
        sep();
        /**
         * 后缀表达式求值：
         * 算法概述：
         *      扫描表达式，如果遇到：
         *          操作数：
         *              入栈
         *          操作符：
         *              从栈中提出两个操作数，用操作数将其执行运算。结果入栈
         */
        testPostfix();

        /**
         * ！！！！！！！！！注意以上两个栈的应用程序都没有检查输入错误，如果输入了一个不正确的后缀表达式，结果将难以预测。
         */

    }

    private static void testPostfix() {
        String input = "352-/61-*";         // read a string from kbd
        println("postfix: " + input);
        int output = doPostfixParse(input);
        println("Evaluates to " + output);
    }

    private static void testInfix() {
        String input = "A+B*(C+(D-E))"; //ABCDE-+*+
        println("infix: " + input);
        // make a translator
        InToPost theTrans = new InToPost(input);
        String output = theTrans.doTrans(); // do the translation
        println("Postfix is " + output);

        input = "((A+B)*C)-D";
        println("\ninfix: " + input);
        // make a translator
        theTrans = new InToPost(input);
        output = theTrans.doTrans(); // do the translation
        println("Postfix is " + output);
    }

    private static void testPriorityQ() {
        PriorityQ thePQ = new PriorityQ(5);
        thePQ.insert(30);
        thePQ.insert(50);
        thePQ.insert(10);
        thePQ.insert(40);
        thePQ.insert(20);

        println("PriorityQ remove: ");
        while( !thePQ.isEmpty() ) {
            long item = thePQ.remove();
            print(item + " ");  // 10, 20, 30, 40, 50
        }  // end while
        println();
    }

    private static void testQueue() {
        Queue<Integer> theQueue = new Queue<>(5);  // queue holds 5 items

        theQueue.insert(10);            // insert 4 items
        theQueue.insert(20);
        theQueue.insert(30);
        theQueue.insert(40);

        theQueue.status();

        theQueue.remove();              // remove 3 items
        theQueue.remove();              //    (10, 20, 30)
        theQueue.remove();

        println("after three times remove");
        theQueue.status();

        theQueue.insert(50);            // insert 4 more items
        theQueue.insert(60);            //    (wraps around)
        theQueue.insert(70);
        theQueue.insert(80);

        println("insert 50、60、70、80");
        theQueue.status();

        println("remove all");
        while( !theQueue.isEmpty() )    // remove and display
        {                            //    all items
            long n = theQueue.remove();  // (40, 50, 60, 70, 80)
            print(n);
            print(" ");
        }
        println();
        theQueue.status();
    }

    private static void testQueueWithCount() {
        QueueWithCount<Integer> theQueue = new QueueWithCount<>(5);  // queue holds 5 items

        theQueue.insert(10);            // insert 4 items
        theQueue.insert(20);
        theQueue.insert(30);
        theQueue.insert(40);

        theQueue.display();

        theQueue.remove();              // remove 3 items
        theQueue.remove();              //    (10, 20, 30)
        theQueue.remove();

        println("after three times remove");
        theQueue.display();

        theQueue.insert(50);            // insert 4 more items
        theQueue.insert(60);            //    (wraps around)
        theQueue.insert(70);
        theQueue.insert(80);

        println("insert 50、60、70、80");
        theQueue.display();

        println("remove all");
        while( !theQueue.isEmpty() )    // remove and display
        {                            //    all items
            long n = theQueue.remove();  // (40, 50, 60, 70, 80)
            print(n);
            print(" ");
        }
        println();
    }

    private static void testBracketChecker() {
        println("BracketChecker: ");

        String input = "(1[3])";
        println("check: " + input);
        print("result: ");
        bracketCheck(input);      // check brackets

        input = "(edf[dd}df)";
        println("check: " + input);
        print("result: ");
        bracketCheck(input);      // check brackets
    }

    private static void testStackX() {
        StackX<Integer> theStack = new StackX<>(10);  // make new stack
        theStack.push(20);               // push items onto stack
        theStack.push(40);
        theStack.push(60);
        theStack.push(80);
        println(theStack.toString());

        print("pop: ");
        while( !theStack.isEmpty() )     // until it's empty,
        {                             // delete item from stack
            long value = theStack.pop();
            print(value);      // display it
            print(" ");
        }  // end while
        println();
    }


    /**
     * 通过栈，检查是否括号是否配对
     * @param input
     */
    private static void bracketCheck(String input) {
        int stackSize = input.length();      // get max stack size
        StackX<Character> theStack = new StackX<>(stackSize);  // make stack

        for(int j=0; j<input.length(); j++) {
            char ch = input.charAt(j);        // get char
            switch(ch) {
                case '{':                      // opening symbols
                case '[':
                case '(':
                    theStack.push(ch);          // push them
                    break;
                case '}':                      // closing symbols
                case ']':
                case ')':
                    if( !theStack.isEmpty() ) {   // if stack not empty,
                        char chx = theStack.pop();  // pop and check
                        if( (ch=='}' && chx!='{') ||
                                (ch==']' && chx!='[') ||
                                (ch==')' && chx!='(') ) {
                            println("Error: "+ch+" at "+j);
                            return;
                        }
                    } else {               // prematurely empty
                        println("Error: " + ch + " at " + j);
                        return;
                    }
                    break;
                default:    // no action on other characters
                    break;
            }  // end switch
        }  // end for
        // at this point, all characters have been processed
        if( theStack.isEmpty() ) {
            println("Right!");
        } else {
            println("Error: missing right delimiter");
        }
    }

    public static int doPostfixParse(String input) {
        StackX<Integer> theStack = new StackX<Integer>(20);             // make new stack
        char ch;
        int j;
        int num1, num2, interAns;

        for(j=0; j<input.length(); j++) {
            ch = input.charAt(j);              // read from input
            theStack.displayStack(""+ch+" ");  // *diagnostic*
            if(ch >= '0' && ch <= '9') {         // if it's a number
                theStack.push((ch - '0')); //   push it
            } else {
                num2 = theStack.pop();          // pop operands
                num1 = theStack.pop();
                switch(ch) {
                    case '+':
                        interAns = num1 + num2;
                        break;
                    case '-':
                        interAns = num1 - num2;
                        break;
                    case '*':
                        interAns = num1 * num2;
                        break;
                    case '/':
                        interAns = num1 / num2;
                        break;
                    default:
                        interAns = 0;
                }  // end switch
                theStack.push(interAns);        // push result
            }  // end else
        }  // end for
        interAns = theStack.pop();            // get answer
        return interAns;
    }  // end doParse()
}
