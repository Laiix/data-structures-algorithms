package com.eussi.ch06_recursion;

import com.eussi.ch06_recursion.util.*;
import com.eussi.util.PrintUtil;

/**
 * @author wangxueming
 * @create 2019-11-12 21:23
 * @description
 */
public class Recursion {
    public static void main(String[] args) {
        /**
         * 递归
         *      递归是种方法(函数)调用自己的编程技术。这听起来似乎有点奇怪,或者甚至像是一个灾难
         * 性的错误。但是递归在编程中却是最有趣,又有惊人高效的技术之一。就像拽着自己的鞋带拔高一
         * 样,在第一次遇到递归时,它似乎让人觉得难以置信。然而,递归不仅可以解决特定的问题,而且它也
         * 为解决很多问题提供了一个独特的概念上的框架。
         *
         * 三角数字
         *      据说毕达哥拉斯理论家,又称一群在毕达哥拉斯(以毕达哥拉斯理论闻名)领导下工作的古希
         * 腊的数学家,发现了在数字序列1,3,6,10,15,21,...(省略号说明这个序列无限地继续下去)
         * 中有一种奇特的联系。你能知道这个序列的下一个数字是什么吗?
         *      这个数列中的第n项是由第n-1项加n得到的。由此,第二项是由第一项(1)加上2,得3。
         * 第三项是由第二项(3)加上3得到6,依此类推
         *      这个序列中的数字被称为三角数字，因为他们可以被形象化的表示成对象的一个三角形排列：
         *                                        *
         *                            *           * *
         *               *            * *         * * *
         *      *        * *          * * *       * * * *
         *     n=1       n=2           n=3          n=4
         *   即：f(n)= (1+n)*n/2 = (n^2+n)/2
         *
         * 首先，可以使用循环查找第n项：
         */
        System.out.println(triangle(3));
        PrintUtil.sep();
        /**
         * 使用递归查找第n项：
         *      循环的方法好像是非常易懂的,但是还可以通过另外一种方式来看这个问题。第n项的值可以
         * 被看成只是两个部分的和,而不是被看作整个序列的和。它们是:
         *      1.第一列(最高的一列),它的值为n。
         *      2.所有剩余列的和。
         * 把责任推给别人
         *      所有的这些方法都可以看作是把责任推给别人。某人让我计算第九个三角数字。我知道这就是
         * 9加上第八个三角数字,所以我叫来Hary并让他来计算第八个三角数字。当我从Hary那里得到
         * 了返回的时候,我就可以用9去加上他告诉我的结果了,并且这就是我的答案了。
         *      Harry知道第八个三角数字是8加上第七个三角数字所得到的结果,所以他找到 Sally,并且让
         * Sally去求第七个三角数字。这个过程持续不断地把问题从一个人这里传递到另一个人那里
         *      什么地方是这个传递的终结呢?在这个地方的人必须不再需要得到另外一个人的帮助就能够
         * 计算出结果。如果这种情况没有发生,那么就会有一个无限的一个人要求另外一个人的链——这是一
         * 种算法的庞氏骗局( Ponzi scheme),它将永远不会结束。在 triangle()方法中目前的情况,意味着
         * 调用自身的方法反复地无限执行,最终它将会使这个程序崩溃。
         * 不推卸责任
         *      为了防止无限重复调用自身的过程,在序列中第一个找到三角数字的人,也就是当n等于1时,
         * 他肯定知道结果是1,没有更小的数字需要询问别人了,也没有什么数字可以去加到其他的数字上
         * 了,所以到此为止不再推卸责任。可以给 triangle方法增加一个条件来表示
         */
        System.out.println(triangle1(3));
        PrintUtil.sep();
        /**
         * 注意，在最内层方法返回1之前，实际上在同一时刻有3个不同的triangle1()方法的实例。
         *
         * 递归方法的特征：
         *      尽管 triangle1方法很短,但是它拥有所有递归算法都具备的关键特征：
         *          调用自身。
         *          当它调用自身的时候,它这样做是为了解决更小的问题。
         *          存在某个足够简单的问题的层次,在这一层算法不需要调用自己就可以直接解答,且返结果。
         *      在递归算法每次调用自身的过程中,参数变小(也许是被多个参数描述的范围变小),这反映
         * 了问题变小或变简单的事实。当参数或者范围达到一定的最小值时,将会触发一个条件,此时方法
         * 不需要调用自身而可以返回。
         *
         * 递归方法有效率吗?
         *      调用一个方法会有一定的额外开销。控制必须从这个调用的位置转移到这个方法的开始处。除
         * 此之外,传给这个方法的参数以及这个方法返回的地址都要被压入到一个内部的栈里,为的是这个
         * 方法可以访问参数值和知道返回到哪里。
         *      就 triangle1这个方法来讲,因为有上述开销而造成的结果,可能 while循环方法执行的速度比
         * 递归的方法快。在此题中,递归的代价也许不算太大。但是如果由于递归方法的存在,造成了太大
         * 规模的方法调用的话,可能会考虑消除递归,在这一章的最后将会详谈一些这方面的问题。
         *      另外一个低效性反映在系统内存空间存储所有的中间参数以及返回值,如果有大量的数据需要
         * 存储，就会引起栈溢出的问题。
         *      人们常常采用递归，是因为它从概念上简化了问题，而不是因为它本质上更有效率。
         */

        /**
         * 数学归纳法
         *      递归就是程序设计中的数学归纳法。数学归纳法是一种通过自身的语汇定义某事物自己的方
         * 法。(语汇也被用于描述证明原理的相关方法。)使用归纳法,可以用数学的方式定义三角数字
         *      tri(n=1)             if n=1
         *      tri(n)=n+tri(n-1)    if n> 1
         *      用自身来定义某事可能看起来是在转圈子,但是事实上它是完全正确的(假设有一个基值情
         * 况)。
         */

        /**
         * 阶乘
         *      计算阶乘是一个递归的经典例子,尽管阶乘不像三角数字那
         * 么直观
         *      其他很多数字学的问题都使用递归的类似方法解决,比如找
         * 两个数的最大公约数(用于分数化简),求一个数的乘方,等等。
         *      再说一次,尽管这些计算可以很好地说明递归,但是它们不太可
         * 能用于实际,因为基于循环的方法效率更高。
         */
        System.out.println(factorial(3));
        PrintUtil.sep();

        /**
         * 全排列单词：
         *      假设单词包含有n个字母
         *      1、全排列最右边的n-1个字母
         *      2、轮换所有n个字母
         *      3、重复以上步骤 n次
         *
         */
        doAnagram();
        PrintUtil.sep();

        /**
         * 递归的二分查找:
         *      recfind()方法反复地调用自己,它每一次调用自己都比上一次的范围更小。当最内层的方法找到了指定的
         * 数据项,也就是值为27的数据项后,方法返回这个数据项的在数组中的下标，即2(正如在有序数
         * 据的显示中看到的一样)。于是这个值依次从每一层 recFind()中返回:最后,find()返回值给类用户。
         *      递归的二分查找和非递归的二分查找有同样的大O效率:O(logN)。递归的二分查找更为简洁
         * 一些,但是它的速度可能会慢一点
         *
         * 分治算法
         *      递归的二分查找法是分治算法的一个例子。把一个大问题分成两个相对来说更小的问题,并且
         * 分别解决每一个小问题。对每一个小问题的解决方法是一样的:把每个小问题分成两个更小的问题
         * 并且解决它们。这个过程一直持续下去直到达到易于求解的基值情况,就不用再继续分了。
         * 尽管正如在第2章中看到的二分查找算法一样,也可以使用非递归的算法,但是分治算法通常
         * 要回到递归。
         *      分治算法常常是一个方法,在这个方法中含有两个对自身的递归调用,分别对应于问题的两个
         * 部分。在二分查找中,就有两个这样的调用,但是只有一个真的执行了。(调用哪一个取决于关键
         * 字的值。)在这一章中,后面将会遇到归并排序,它是真正执行了两个递归调用(对分成两半的数
         * 组分别进行排序)
         */
        binarySearch();
        PrintUtil.sep();

        /**
         * 汉诺( Hanoi)塔问题
         *      汉诺塔问题是由很多放置在三个塔座上的盘子组成的一个古老的难题,
         * 所有盘子的直径是不同的,并且盘子中央都有个洞以使它们刚好可以放到塔座上。所有的盘
         * 子刚开始都放在塔座A上。这个难题的目标是将所有的盘子都从塔座A移动到塔座C上。每一次
         * 只可以移动一个盘子,并且任何一个盘子都不可以放在比自己小的盘子之上。
         *      在印度的某地流传着一个古老的神话,在一个偏僻的庙宇,僧侣们日夜不停的劳动,要把64
         * 个金制的盘子从三个镶嵌着钻石的塔中的一个搬到另外一个里。当他们完成这个任务的时候,世界
         * 就将灭亡了。这可能会使人感到某种恐慌,但是,如果知道在这个难题中即使只是搬比64少得多
         * 的盘子也要花费多么长时间的话,这种恐慌就会消失了。
         *
         * 移动的子树
         *      在塔座A上盘子初始的树形(或金字塔形)排列称为一棵树。(这种树和其他地方提到的
         * 作为数据存储结构的树无关。) 注意到生成盘子的较小的树形堆是问
         * 题解决过程中的一步。这些所含盘子数小于盘子总数的较小的树称为子树。举例来说,如果要移动
         * 4个盘子,会发现中间的一个状态是有3个盘子的子树在塔座B上
         *      这些子树在这个难题的解决过程中会形成多次。子树形成多次是因为一棵子树的形式是把一个
         * 更大的盘子从一个塔座上转移到另一个塔座上的惟一方法:所有的小盘子都必须先放置在一个中介
         * 的塔座上,在这个中介的塔座上自然就会形成一棵子树
         *      当手动地解决这个难题的时候,有一个经验法则,可以提供帮助。如果试图要移动的子树含有
         * 奇数个盘子,开始时直接把最顶端的盘子移动到想要把这棵子树移动到的那个塔座上。如果试图要
         * 移动一棵含有偶数个盘子的子树,那么开始时要把最顶端的盘子移动到中介塔座上
         *
         * 递归的算法：
         *      用子树的概念可以递归的表示出汉诺塔难题的的解决办法。假设想要把所有的盘子从源塔座上
         * (称为S)移动到目标塔座上(称为D)。有一个可以使用的中介塔座(称为I)。假定在塔座S上有
         * n个盘子。算法如下:
         *      1.从塔座S移动包含上面的n-1个盘子的子树到塔座I上。
         *      2.从塔座S移动剩余的盘子(最大的盘子)到塔座D上
         *      3.从塔座I移动子树到塔座D
         * 当开始的时候,源塔座是A,中介塔座是B,目标塔座是C。图6.13显示了这种情况的三个步
         * 骤
         */

        doTowersNoOutput(3, 'A', 'B', 'C');
        System.out.println("\n+++++++++++++++++++++++++\n");
        doTowers(0, 3, 'A', 'B', 'C');
        PrintUtil.sep();

        /**
         * 归并排序
         *      最后一个递归的例子是归并排序。归并排序比在第3章“简单排序”中看到的排序方法要有效
         * 得多,至少在速度上是这样的。冒泡排序、插入排序和选择排序要用O(N2)时间,而归并排序只要
         * O(N*logN)。归并排序要比简单排序快多少,如果N(被排序的数据项的数目)是1000那么N^2就是
         * 1000000, N*logN只是4000如果为这么多数据项排序用归并排序的话需要40秒,那么用插入排序则
         * 会需要将近28个小时。
         *      归并排序也相当容易实现。归并排序在概念上比将要在下一章中看到的快速排序和希尔排序都
         * 要容易理解,
         *      归并排序的一个缺点是它需要在存储器中有另一个大小等于被排序的数据项数目的数组。如果
         * 初始数组几乎占满整个存储器,那么归并排序将不能工作。但是,如果有足够的空间,归并排序会
         * 是一个很好的选择。
         *
         * 归并两个有序数组
         *      归并算法的中心是归并两个已经有序的数组。归并两个有序数组A和B,就生成了第三个数组
         * C,数组C包含数组A和B的所有数据项,并且使它们有序的排列在数组C中。首先考察归并的过
         * 程;然后看它是如何在排序中使用的
         *      假设有两个有序数组,不要求有相同的大小。设数组A有4个数据项,数组B有6个数据项
         * 它们要被归并到数组C中,开始时数组C有10个空的存储空间。
         *      归并过程如下：
         */
        merge();
        PrintUtil.sep();
        /**
         * 通过归并进行排序
         *      归并排序的思想是把一个数组分成两半,排序每一半,然后用 merge(方法把数组的两半归并成
         * 个有序的数组。如何来为每一部分排序呢?这一章讲述的是递归,所以大概已经有答案了:把每
         * 半都分成两个四分之一,对每个四分之一部分排序,然后把它们归并成一个有序的一半。
         * 类似的,每一对八分之一部分归并成一个有序的四分之一部分,每一对十六分之一部分归并成
         * 个有序的八分之一部分,依此类推。反复地分割数组,直到得到的子数组只含有一个数据项。这
         * 就是基值条件;设定只有一个数据项的数组是有序的。
         *      前面已经看到递归方法在每次调用自身方法的时候通常某个参数的大小都会减小,并且方法每
         * 次返回时参数值又恢复到以前。在 mergeSort方法中,每一次这个方法调用自身的时候排列都会被
         * 分成两部分,并且方法每一次返回时都会把两个较小的排列合并成一个更大的排列
         *      当 mergeSort发现两个只有一个数据项的数组时,它就返回,把这两个数据项归并到一个有两
         * 个数据项的有序数组中。每个生成的一对两个数据项的数组又被合并成一个有4个数据项的有序数组。
         * 这个过程一直持续下去，数组越来越大直到整个数组有序
         */
        mergeSort();
        PrintUtil.sep();
        /**
         * 归并排序的效率
         *      正如前面提到的那样,归并排序的运行时间是O(N*logN)。如何知道这个时间的呢?首先看在
         * 这个算法执行的过程当中,如何计算一个数据项被复制的次数以及和其他数据项比较的次数。假设
         * 复制和比较是最费时的操作,递归的调用和返回不增加额外的开销
         * 复制的次数
         *      考虑数据的个数N为2的乘方时，可想像成一棵树，叶子结点是两个数据，一共时log2N层，
         * 每一层需要复制2N次（包含两次复制），则赋值次数为2N*log2N
         * 比较的次数
         *      在归并排序算法中,比较的次数总是比复制的次数略微少一些的。那么少多少呢?假设数据项
         * 的个数是2的乘方,对于每一个独立的归并操作,比较的最大次数总是比正在被归并的数据项个数
         * 少一,并且比较的最少次数是正在被归并的数据项数目的一半。
         *
         * 所以，总的来说，归并排序是O(N*logN)
         */


        /**
         * 消除递归
         *      有一些算法趋向于用递归的方法,另一些算法则不是。正如前面已经看到的那样,递归的
         * triangle()和 factorial()方法可以用一个简单的循环来实现,那样效率更高。但是,各种分治算法,比
         * 如归并排序的递归函数,能工作得非常好。
         *      一个算法作为一个递归的方法通常从概念上很容易理解,但是,在实际的运用中证明递归算法
         * 的效率不太高。在这种情况下,把递归的算法转换成非递归的算法是非常有用的。这种转换经常会
         * 用到栈。
         * 递归和栈
         *      递归和栈之间有一种紧密的联系。事实上,大部分的编译器都是使用栈来实现递归的。正如我
         * 们曾提到过的,当调用一个方法的时候,编译器会把这个方法的所有参数及其返回地址(这个方法
         * 返回时控制到达的地方)都压入栈中,然后把控制转移给这个方法。当这个方法返回的时候,这些
         * 值退栈。参数消失了,并且控制权重新回到返回地址处。
         *
         * 模拟一个递归方法:
         *      这个程序模拟了一个方法。
         */
        StackTriangle stackTriangle = new StackTriangle();
        stackTriangle.doTriangle(4);
        PrintUtil.sep();
        /**
         * 这证明了什么?
         *      在stackTriangle中,有一个程序,它多少是系统地把一个递归程序转换成了使
         * 用栈的程序。这表明对于任意一个递归程序都有可能做出这种转换,实际上这就是一个例证。
         *      如果做一些额外的工作,可以系统地精炼这里给出的代码,并且还可以化简它,甚至可以完全
         * 消除 switch语句,使代码更有效率
         *      然而在实践中,人们往往从一开始就重新思考基于栈的算法,而不是从递归的算法转化,这样
         * 作更为实用。如下：
         */
        StackTriangle2 stackTriangle2 = new StackTriangle2();
        stackTriangle2.doTriangle(4);
        PrintUtil.sep();

        /**
         * 深度优先搜索实现排列组合
         *
         * 问题1：
         * 假设袋子里有编号为1,2,...,m这m个球。现在每次从袋子中取一个球几下编号，放回袋中再取，取n次作为一组，枚举所有可能的情况。
         * 分析：
         * 每一次取都有m种可能的情况，因此一共有种情况。
         * 这里我们取m = 3, n = 4，则有3^4=81种不同的情况。
         *
         * 方法参数：
         *      minv   - 小球编号的最小值
         *      maxv   - 小球编号的最大值
         *      curnum - 当前已经确定的小球的个数
         *      maxnum - 要选取的小球的数目
         */
        permuteCombine_1(1, 3, 0, 3);
        PrintUtil.sep();

        /**
         * 问题2：                                                                                                                                                                        
         * 假设袋子里有编号为1,2,...,m这m个球。先后从袋子中取出n个球，依次记录编号，枚举所有可能的情况。
         * 分析：
         * 这是排列问题，应该有A(m,n)种情况。
         * 这里取m = 5, n = 3，A(5,3)=5*4*3=60。
         * 和问题1相比，唯一的区别是排列中不可以有重复。因此开了used数组用以标记是否已经访问。
         *
         * 方法参数：
         *      minv   - 小球编号的最小值
         *      maxv   - 小球编号的最大值
         *      curnum - 当前已经确定的小球的个数
         *      maxnum - 要选取的小球的数目
         */
        permuteCombine_2(1, 3, 0, 3);
        PrintUtil.sep();

        /**
         * 问题3：                                                                                                                                                                        
         * 从m个球里（编号为1,2,3...,m）一次取n个球，其中m>n，记录取出球的编号，枚举所有的可能性。
         * 分析：
         * 这是组合问题。应该有C(m,n)种可能性。
         * 这里，我们取m = 8, n = 4. 因此有C(8,4)=A(8,4)/4!=(8*7*6*5)/(4*3*2*1)=70种可能。
         *
         * 方法参数：
         *     curmaxv - 当前已经抓取小球中最大的编号
         *     maxv    - 待抓取小球中最大的编号
         *     curnum - 当前已经抓取的小球数目
         *     maxnum - 需要抓取小球的数目
         *
         */
        permuteCombine_3(0, 4, 0, 3);

    }

    public static void permuteCombine_1(int minv, int maxv, int curnum, int maxnum){
        PermuteCombine_1.dfs(minv, maxv, curnum, maxnum);
    }

    public static void permuteCombine_2(int minv, int maxv, int curnum, int maxnum){
        PermuteCombine_2.dfs(minv, maxv, curnum, maxnum);
    }

    public static void permuteCombine_3(int curmaxv, int maxv, int curnum, int maxnum){
        PermuteCombine_3.dfs(curmaxv, maxv, curnum, maxnum);
    }

    public static void mergeSort()
    {
        int maxSize = 100;             // array size
        DArray arr;                    // reference to array
        arr = new DArray(maxSize);     // create the array

        arr.insert(64);                // insert items
        arr.insert(21);
        arr.insert(33);
        arr.insert(70);
        arr.insert(12);
        arr.insert(85);
        arr.insert(44);
        arr.insert(3);
        arr.insert(99);
        arr.insert(0);
        arr.insert(108);
        arr.insert(36);

        arr.display();                 // display items

        arr.mergeSort();               // merge sort the array

        arr.display();                 // display items again
    }  // end main()

    public static void merge() {
        int[] arrayA = {23, 47, 81, 95};
        int[] arrayB = {7, 14, 39, 55, 62, 74};
        int[] arrayC = new int[10];
        Merge merge = new Merge();
        merge.merge(arrayA, 4, arrayB, 6, arrayC);
        merge.display(arrayC, 10);
    }

    public static void doTowersNoOutput(int topN, char src, char inter, char dest) {
        if(topN==1) {
            System.out.println("Base case: move disk 1 from " + src + " to " + dest);
        } else {
            doTowersNoOutput(topN-1, src, dest, inter);   // src to inter

            System.out.println("Move bottom disk " + topN + " from " + src + " to " + dest);

            doTowersNoOutput(topN-1, inter, src, dest);   // inter to dest
        }
    }

    public static void doTowers(int level, int topN, char src, char inter, char dest) {
        printInfo(level, "Enter (" + topN + " disks): " + "s=" + src + ", i=" + inter + ", d=" + dest);
        if(topN==1) {
            printInfo(level+1, "Base case: move disk 1 from " + src + " to " + dest);
        } else {
            doTowers(level+1, topN-1, src, dest, inter);   // src to inter

            printInfo(level+1, "Move bottom disk " + topN + " from " + src + " to " + dest);

            doTowers(level+1, topN-1, inter, src, dest);   // inter to dest
        }
        printInfo(level, "Return (" + topN + " disks)");
    }

    private static void printInfo(int level, String s) {
        for(int i=0; i<level; i++) {
            System.out.print("\t");
        }
        System.out.println(s);
    }

    public static void binarySearch() {
        int maxSize = 100;             // array size
        OrdArray arr;                  // reference to array
        arr = new OrdArray(maxSize);   // create the array

        arr.insert(72);                // insert items
        arr.insert(90);
        arr.insert(45);
        arr.insert(126);
        arr.insert(54);
        arr.insert(99);
        arr.insert(144);
        arr.insert(27);
        arr.insert(135);
        arr.insert(81);
        arr.insert(18);
        arr.insert(108);
        arr.insert(9);
        arr.insert(117);
        arr.insert(63);
        arr.insert(36);

        arr.display();                 // display array

        int searchKey = 27;            // search for item
        if( arr.find(searchKey) != arr.size() )
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);
    }

    public static void doAnagram() {
        Anagram anagram = new Anagram("cat");
        anagram.doExecute();
    }

    public static int factorial(int n)
    {
        System.out.println("calculate " + n);
        if(n==1) {
            System.out.println("Returning 1");
            return 1;
        }
        else {
            int temp = n * factorial(n-1);
            System.out.println("Returning " + temp);
            return temp;
        }
    }

    /**
     * 递归调用
     * @param n
     * @return
     */
    public static int triangle1(int n)
    {
        System.out.println("calculate " + n);
        if(n==1) {
            System.out.println("Returning 1");
            return 1;
        }
        else {
            int temp = n + triangle1(n-1);
            System.out.println("Returning " + temp);
            return temp;
        }
    }

    /**
     * 循环调用
     * @param n
     * @return
     */
    public static int triangle(int n) {
        int total = 0;
        while(n>0) {
            total += n;
            --n;
        }
        return total;
    }
}
