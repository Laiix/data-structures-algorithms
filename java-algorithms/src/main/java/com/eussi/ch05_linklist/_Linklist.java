package com.eussi.ch05_linklist;

import com.eussi.ch05_linklist.util.*;
import com.eussi.util.PrintUtil;

/**
 * @author wangxueming
 * @create 2019-10-29 0:05
 * @description
 */
public class _Linklist {
    public static void main(String[] args) {
        /**
         * 链表
         *      在第2章“数组”中,我们看到数组作为数据存储结构有一定的缺陷。在无序数组中,搜索
         * 是低效的;而在有序数组中,插入效率又很低:不管在哪一种数组中删除效率都很低。况且一个
         * 数组创建后,它的大小是不可改变的。
         *      在本章中,我们将看到一种新的数据存储结构,它可以解决上面的一些问题。这种数据存储结
         * 构就是链表。链表可能是继数组之后第二种使用得最广泛的通用存储结构。
         *      链表的机制灵活,用途广泛,它适用于许多通用的数据库。它也可以取代数组,作为其他存储
         * 结构的基础,例如栈,队列。除非需要频繁通过下标随机访问各个数据,否则在很多使用数组的地
         * 方都可以用链表代替
         *      链表虽不能解决数据存储中的所有问题,但是它确实用途广泛,在概念上也比其他的常用结构
         * (例如树)简单。随着问题的深入,我们将探讨链表的优点和缺点。
         *      在本章中我们将学习单链表、双端链表、有序链表、双向链表和有迭代器的链表(迭代器是用
         * 来随机访问链表元素的一种方法)。还会实践一下抽象数据类型(ADT)的思想:如何用ADT描述
         * 栈和队列,以及如何用链表代替数组来实现栈和队列。
         *
         * 链节点
         *      在链表中,每个数据项都被包含在“链结点”(Link)中。一个链结点是某个类的对象,这个类
         * 可以叫做Link。因为一个链表中有许多类似的链结点,所以有必要用一个不同于链表的类来表达链
         * 结点。每个Link对象中都包含一个对下一个链结点引用的字段(通常叫做next)。但是链表本身的
         * 对象中有一个字段指向对第一个链结点的引用。
         *
         * 关系,而不是位置
         *      让我们来举例说明链表不同于数组的主要特点之一。在一个数组中,每一项占用一个特定的位
         * 置。这个位置可以用一个下标号直接访问。它就像一排房子,你可以凭地址找到其中特定的一间。
         *      在链表中,寻找一个特定元素的惟一方法就是沿着这个元素的链一直向下寻找。它很像人们之
         * 间的关系。可能你问Hary,Bob在哪。Hary不知道,但是他想Jane可能知道,所以你又去问Jane。
         * Jane看到Bob和Saly一起离开了办公室,所以你打 Sally的手机,她说她在 Peter的办公室和Bob
         * 分开了,所以…但是总有线索。不能直接访问到数据项:必须使用数据之间的关系来定位它。你
         * 从第一项开始,到第二个,然后到第三个,直到发现要找的那个数据项。
         */

        /**
         * 单链表
         */
        testLinklist();
        PrintUtil.sep();
        /**
         * 查找和删除指定链节点
         */
        testLinklist2();
        PrintUtil.sep();

        /**
         * 双端链表
         *      双端链表与传统链表非常相似，但是他有一个新增的特性：即对最后一个链节点的引用，就像对第
         * 一个链节点的引用一样。
         *      对最后一个链节点的引用允许像在表头一样，在表尾直接插入一个链节点。当然，仍然可以在普通
         * 单链表的表尾插入一个链节点，方法是遍历整个链表直到到达表尾。但这种方式效率很低。
         *      像访问表头一样访问表尾的特性，使双端链表更适合于一些普通链表不方便操作的场合，队列的实
         * 现就是这样的一种情况。
         *      双端链表也不能有助于删除最后一个链节点，因为没有一个引用指向倒数第二个链节点。如果最后
         * 一个链节点被删除，倒数第二个链节点的next字段应该变成null值。为了方便删除最后一个链节点，需要
         * 一个双向链表。（当然也可以遍历删除，但是效率较低）
         */
        testFirstLastList();
        PrintUtil.sep();
        /**
         * 链表的效率
         *      在表头插入和删除速度很快。仅需要改变一两个引用值,所以花费O(1)的时间。
         *      平均起来,查找、删除和在指定链结点后面插入都需要搜索链表中的一半链结点。需要O(N)
         * 次比较。在数组中执行这些操作也需要O(N)次比较,但是链表仍然要快一些,因为当插入和删除链
         * 结点时,链表不需要移动任何东西。增加的效率是很显著的,特别是当复制时间远远大于比较时间
         * 的时候
         *      当然,链表比数组优越的另外一个重要方面是链表需要多少内存就可以用多少内存,并且可以
         * 扩展到所有可用内存。数组的大小在它创建的时候就固定了;所以经常由于数组太大导致效率低下,
         * 或者数组太小导致空间溢出。
         *      向量是一种可扩展的数组,它可以通过可变长度解决这个问题,但是
         * 它经常只允许以固定大小的增量扩展(例如快要溢出的时候,就增加一倍数组容量)。这个解决方
         * 案在内存使用效率上来说还是要比链表的低。
         */


        /**
         * 抽象数据类型
         *      接下来将会转换角度,讨论一个比链表更加广泛的话题:抽象数据类型(ADT)什么是ADT?
         * 简单说来,它是一种考虑数据结构的方式:着重于它做了什么,而忽略它是怎么做的
         *      栈和队列都是ADT的例子。前面已经看到栈和队列都可以用数组来实现。用链表同样可以实现
         * 栈和队列，如用单链表实现栈，双向链表实现队列。这将展示栈和队列的“抽象”特性:即如何脱离
         * 具体实现来考虑栈和队列。
         *      栈和队列是概念上的实体,独立于它们的具体实现。用数组或是用链表实现栈都是一样的。栈
         * 的重要性在于它的 push(操作和pop(操作,以及如何使用它们;而不是实现这些操作的内在机制。
         *      什么时候应该使用链表而不是数组来实现栈或队列呢?这一点要取决于是否能精确地预测栈
         * 或队列需要容纳的数据量。如果这点不甚清楚,链表就比数组表现出更好的适应性。两者都很快
         * 所以速度可能不是考虑重点。
         *
         * 数据类型和抽象
         *      抽象数据类型”这个术语从何而来?首先看看“数据类型”这部分,再来考虑“抽象”。
         *
         * 数据类型
         *      “数据类型”一词用在很多地方。它首先表示内置的类型,例如int型和 double型。这可能是
         * 听到这个词后首先想到的。
         *      当谈论一个简单类型时,实际上涉及到两件事:拥有特定特征的数据项和在数据上允许的操作。
         * 例如,Java中的int型变量是整数,取值范围在-2l47483648和+2147483647之间,还能用各种操作
         * 符,+、-、*、!等等对其进行操作。数据类型允许的操作是它本身不可分离的部分,理解类型包括
         * 理解什么样的操作可以应用在该类型之上。
         *      随着面向对象编程的出现,现在可以用类来创建自己的数据类型。这些数据类型中的一部分表
         * 示数值量,使用的方式和基本类型类似。例如,可以为时间定义一个类(拥有小时、分钟和秒等字
         * 段),为分数定义一个类(拥有分子和分母等字段),为超长数字定义一个类(字符串中的字符表示
         * 数字)。所有这些类都可以像int型和 double型一样做加法和减法,只是在Java语言中必须使用方
         * 法而不是像‘+’或‘-’这样的操作符,这些方法使用类似于add()和sub()的函数标记符号。
         *      "数据类型"这个短语好像很自然的适合那些数值量的类。然而,它也适用于没有这些量化属
         * 性的类。事实上,“任何”类都代表一个数据类型,从这个意义上来说,类是由数据(字段)和数
         * 据上允许的操作(方法)组成的。
         *      更广泛一点,当一个数据存储结构(例如栈和队列)被表示为一个类时,它也成为了一个数据
         * 类型。栈和int类型在很多方面都不同,但它们都被定义为一组具有一定排列规律的数据和在此数
         * 据上的操作集合。
         *
         * 抽象
         *      抽象这个词的意思是“不考虑细节的描述和实现。”抽象是事物的本质和重要特征。例如,总
         * 统办公室是一个抽象,它并不考虑哪一个人碰巧成为这个办公室的主人。总统办公室的权力和责任
         * 依旧,但是总统会随着任职和去职而来去。
         *      因此,在面向对象编程中,一个抽象数据类型是一个类,且不考虑它的实现。它是对类中数据
         * 域)的描述和能够在数据上执行的一系列操作(方法)以及如何使用这些操作的说明。每个方法
         * 如何执行任务的细节肯定不包括在内。作为类的用户,只会被告知可以调用哪些方法,如何调用它
         * 们,以及可望得到的结果,但是不包括内部如何运作的。
         *      当“抽象数据类型”用于像栈和队列这样的数据结构时,它的意义被进一步扩展了。和其他类
         * 样,它意味着数据和在数据上执行的操作,即使在这种情况下,如何存储数据的基本原则对于类
         * 用户来说也是不可见的。用户不仅不知道方法怎样运作,也不知道数据是如何存储的。
         *      对于栈来说,用户只知道 push()方法和pop()方法(也许还有一些其他方法)的存在和如何用它
         * 们工作。用户不需要(至少不经常需要)知道方法内部如何运作,或者数据是否存储在数组里、链
         * 表里或是例如树等其他数据结构中
         *
         * 接口
         *      ADT有一个经常被叫做“接口”的规范。它是给类用户看的,通常是类的公有方法。在栈中,
         * push()方法、pop()方法和其他类似的方法形成了接口。
         * ADT列表
         *      现在已经了解了什么是抽象数据类型,所以可以谈及另外一个术语:“列表”。列表(有时叫做
         * 线性表)是一组线性排列的数据项。也就是说,它们以一定的方式串接起来,像一根线上的珠子或
         * 条街上的房子。列表支持一定的基本操作。可以插入某一项,删除某一项,还有经常从某个特定
         * 位置读出一项(例如,读出第三项)。
         *      不要把本章讲的链表和ADT列表搞混。列表由它的接口定义：即用于和它交互的特定方法。这
         * 个接口可以用不同的结构实现,包括数组、链表。列表是这些数据结构的抽象。
         *
         * 作为设计工具的ADT
         *      ADT的概念在软件设计过程中也是有用的。如果需要存储数据,那么就从考虑需要在数据上实
         * 现的操作开始。需要存取最后一个插入的数据项吗?还是第一个?是特定值的项?还是在特定位置
         * 的项?回答这些问题会引出ADT的定义。只有在完整定义了ADT后,才应该考虑细节问题,例如
         * 如何表示数据,如何编码使方法可以存取数据等等
         *      通过从ADT规范中剔除实现的细节,可以简化设计过程。在未来的某个时刻,易于修改实现。
         * 如果用户只接触ADT接口,应该可以在不“干扰”用户代码的情况下修改接口的实现。
         *      当然,一旦设计好ADT,必须仔细选择内部的数据结构,以使规定的操作的效率尽可能高。例
         * 如,如果需要随机存取元素N,那么用链表表示就不够好,因为对链表来说,随机访问不是一个高
         * 效的操作。选择数组会得到较好的效果。
         *      注意
         *          记住,ADT只是一个概念性的工具。数据存储结构不能截然地分成一些是ADT,另一些用来
         *      实现ADT。例如,链表不需要封装成一个列表接口才可用;它本身就可以作为一个ADT,然
         *      而它也可以实现其他数据类型,例如队列。链表能够用数组来实现,一个数组类型的结构也可
         *      以用链表实现。在给定环境下,必须确定什么是ADT,什么是更基本的结构。
         *
         */


        /**
         * 有序链表
         *      讨论至今,在链表中,还没有要求数据有序存储。然而对于某些应用来说,在链表中保持数据
         * 有序是有用的。具有这个特性的链表叫作“有序链表”。
         *      在有序链表中,数据是按照关键值有序排列的。有序链表的删除常常是只限于删除在链表头部
         * 的最小(或者最大)链结点。不过,有时也用 find()方法和 delete()方法在整个链表中搜索某一特定
         * 点
         *      一般,在大多数需要使用有序数组的场合也可以使用有序链表。有序链表优于有序数组的地方
         * 是插入的速度(因为元素不需要移动),另外链表可以扩展到全部有效的使用内存,而数组只能局
         * 限于一个固定的大小中。但是,有序链表实现起来比有序数组更困难一些。
         */
        testSortedList();
        PrintUtil.sep();
        /**
         * 有序链表的效率
         *      在有序链表插入和删除某一项最多需要O(N)次比较(平均N/2),因为必须沿着链表上一步
         * 步走才能找到正确的位置。然而,可以在O(1)的时间内找到或删除最小值,因为它总在表头。如果
         * 个应用频繁地存取最小项,且不需要快速的插入,那么有序链表是一个有效的方案选择。例如,
         * 优先级队列可以用有序链表来实现。
         */

        /**
         * 表插入排序
         *      有序链表可以用于一种高效的排序机制。假设有一个无序数组。如果从这个数组中取出数据,
         * 然后一个一个地插入有序链表,它们自动地按顺序排列。把它们从有序表中删除,重新放入数组,
         * 那么数组就会排好序了
         *      这种排序方式总体上比在数组中用常用的插入排序效率更高一些,这是因为这种方式进行的复
         * 制次数少一些,用数组的插入排序算法在第3章“简单排序”中有所描述。它仍然是一个时间级为
         * O(N²)的过程,因为在有序链表中每插入一个新的链结点,平均要与一半已存在数据进行比较,如
         * 果插入N个新数据,就进行了N²/4次比较。每一个链结点只进行两次复制:一次从数组到链表,
         * 一次从链表到数组。在数组中进行插入排序需要N²次移动,相比之下,2*N次移动更好。
         *      和基于数组的插入排序相比,表插入排序有一个缺点,就是它要开辟差不多两倍的空间:数组
         * 和链表必须同时在内存中存在。但如果有现成的有序链表类可用,那么用表插入排序对不太大的数
         * 组排序是比较便利的。
         */
        testInsertSort();
        PrintUtil.sep();

        /**
         * 双向链表（不要和双端链表产生混淆）
         *      传统链表的一个潜在问题是沿链表的反向遍历是困难的。双向链表提供了向前遍历的能力。
         * 原因在于每个链节点有两个指向其他链节点的引用，而不是一个。一个向普通链表一样指向下一
         * 个链节点，第二个指向前一个链节点。
         *      双向链表的缺点是每次插入或删除一个链结点的时候,要处理四个链结点的引用,而不是两个。
         * 两个连接前一个链结点,两个连接后一个链结点。当然,由于多了两个引用,链结点的占用空间也
         * 变大了一点。
         *      双向链表不必是双端链表(保持一个对链表最后一个元素的引用),但这种方式是有用的,所
         * 以在后面的例子中将包含双端的性质。
         *
         */
        testDoublyLinkedList();
        PrintUtil.sep();
        /**
         * 基于双向链表的双端队列
         *      双向链表可以用来作为双端队列的基础。在双端队列中,可以从任何一
         * 头插入和删除,双向链表提供了这个能力。见习题 5.2
         */


        /**
         * 迭代器
         *      已经看到链表的用户怎样使用 find()方法来查找一个含有给定值的链结点。这个方法从表头开
         * 始考察每个链结点,直到找到一个链结点的值和给定值匹配。其他的一些操作,例如删除指定链结
         * 点,在指定链结点的前面或后面插入新链结点,也含有链表上的搜索工作,以找到指定的链结点
         * 然而,这些方法没有提供给用户任何遍历上的控制手段,以便找到指定链结点。
         *      假定你要遍历一个链表,并在某些特定的链结点上执行一些操作。例如,用一个链表存储的职
         * 员表。你可能需要提高所有拿最低工资的员工的工资,而不影晌那些已经高于最低工资的员工。或
         * 者假设一个订阅邮件用户的链表,你需要删除所有近六个月没有订阅任何邮件的用户
         *      在数组中,这种操作非常容易,因为可以用数组下标跟踪所在位置。可以在这个链结点上进行
         * 操作,然后通过下标指向下一项,看那一这项是否符合操作条件。然而在链表中,链结点没有固定
         * 的下标。怎样才能提供给链表用户类似于数组下标的东西呢?虽然可以反复使用find方法在链表
         * 中查找到合乎要求的链结点,但是为查找每个链结点这个方法需要进行很多次比较。如果能从链结
         * 点到链结点步进,检查每个链结点是否符合某个标准,若符合标准就执行适当的操作,这样效率会
         * 高得多。
         */
        testIterator();
        /**
         * 选代器指向哪里?
         *      迭代器类的一个设计问题是决定在不同的操作后,迭代器应该指向哪里。
         *      当用 delete Current删除一项后,迭代器应该放在下一链结点,前一链结点,还是回到表头呢?
         * 把迭代器保持在被删除链结点的附近是方便的,因为类的用户将在那里执行其他的操作。然而,不
         * 能把它移动到前一个结果,因为无法把链表的 previous字段置成前一项。(要完成这个任务,需要
         * 个双向链表。)解决办法是把迭代器移动到被删除链结点的下一个链结点。如果恰巧删除链表的
         * 最后一个数据项,迭代器复位指向表头。
         *      执行 insertBefore()方法和 insertAfter()方法后,让 current指向新插入的链结点。
         * atEnd()方法
         *      还有关于atEnd()方法的另一个问题。有两种做法:当迭代器指向链表最后一个有效链结点时,
         * 它返回ture,或者当迭代器指向最后一个链结点的“下一个”时(这时,它不是指向一个有效链结
         * 点),它返回true。
         *      用第一种方法,按这种循环条件,遍历链表显得很笨拙。因为需要在最后一个链结点上仍执行
         * 循环体内的操作,然而一旦检查出它是最后一个链结点,循环却中止了,没有来得及做操作。
         *      在第二种方法中,一直不知是否到链表的结尾,一旦发现到了表尾,想对最后一个链结点做什
         * 么事都已经太晚了。(例如,找到最后一个链结点,并删除它。)这是因为当 atEnd()变成true时,迭
         * 代器将不再指向最后的链结点(或其他确切的有效链结点),迭代器在单链表中也不能“倒退”。
         * 所以在这里还是采用第一种方法,用这种方法迭代器总是能指向一个有效链结点。然而,正如
         * 下面将要看到的那样,在写这种遍历链表的循环时要格外小心
         */

    }

    private static void testIterator() {
        LinkList3 theList = new LinkList3();           // new list
        ListIterator iter1 = theList.getIterator();  // new iter
        long value;

        iter1.insertAfter(20);             // insert items
        iter1.insertAfter(40);
        iter1.insertAfter(80);
        iter1.insertBefore(60);

        System.out.print("Enter first letter of show, reset, ");
        System.out.println("next, get, before, after, delete: ");
        for(char c:new char[]{'s','g','b','s','g','a','s','g','r','g', 'c', 'd', 's', 'g'}) {
            System.out.println("Let's say I have input:" + c);
            int choice = c;         // get user's option
            switch(choice)
            {
                case 's':                    // show list
                    if( !theList.isEmpty() )
                        theList.displayList();
                    else
                        System.out.println("List is empty");
                    break;
                case 'r':                    // reset (to first)
                    iter1.reset();
                    break;
                case 'n':                    // advance to next item
                    if( !theList.isEmpty() && !iter1.atEnd() )
                        iter1.nextLink();
                    else
                        System.out.println("Can't go to next link");
                    break;
                case 'g':                    // get current item
                    if( !theList.isEmpty() )
                    {
                        value = iter1.getCurrent().dData;
                        System.out.println("Returned " + value);
                    }
                    else
                        System.out.println("List is empty");
                    break;
                case 'b':                    // insert before current
                    value = 9999;
                    System.out.println("Enter value to insert: " + value);
                    iter1.insertBefore(value);
                    break;
                case 'a':                    // insert after current
                    value = 8888;
                    System.out.println("Enter value to insert: " + value);
                    iter1.insertAfter(value);
                    break;
                case 'd':                    // delete current item
                    if( !theList.isEmpty() )
                    {
                        value = iter1.deleteCurrent();
                        System.out.println("Deleted " + value);
                    }
                    else
                        System.out.println("Can't delete");
                    break;
                default:
                    System.out.println("Invalid entry");
            }  // end switch
        }  // end while
        System.out.println("==========================");
        iter1.reset();
        value = iter1.getCurrent().dData;
        System.out.print(value + " ");
        while(!iter1.atEnd()) {             //atEnd的实现是current指向最后一个时，作为标志
            iter1.nextLink();               //以上代码之所以可以完成遍历，注意，在循环前已经输出第一个，在循环中，调用了next
            value = iter1.getCurrent().dData;   //while条件只能进行到链表的第二个元素
            System.out.print(value + " ");
        }
    }

    private static void testDoublyLinkedList() {
        DoublyLinkedList theList = new DoublyLinkedList();

        theList.insertFirst(22);      // insert at front
        theList.insertFirst(44);
        theList.insertFirst(66);

        theList.insertLast(11);       // insert at rear
        theList.insertLast(33);
        theList.insertLast(55);

        theList.displayForward();     // display list forward
        theList.displayBackward();    // display list backward

        theList.deleteFirst();        // delete first item
        theList.deleteLast();         // delete last item
        theList.deleteKey(11);        // delete item with key 11

        theList.displayForward();     // display list forward

        theList.insertAfter(22, 77);  // insert 77 after 22
        theList.insertAfter(33, 88);  // insert 88 after 33

        theList.displayForward();     // display list forward
    }

    private static void testInsertSort() {
        int size = 10;
        // create array of links
        Link2[] linkArray = new Link2[size];

        for(int j=0; j<size; j++)  // fill array with links
        {                            // random number
            int n = (int)(Math.random()*99);
            Link2 newLink = new Link2(n);  // make link
            linkArray[j] = newLink;      // put in array
        }
        // display array contents
        System.out.print("Unsorted array: ");
        for(int j=0; j<size; j++)
            System.out.print( linkArray[j].dData + " " );
        System.out.println("");

        // create new list
        SortedList2 theSortedList = new SortedList2(linkArray);

        for(int j=0; j<size; j++)  // links from list to array
            linkArray[j] = theSortedList.remove();

        // display array contents
        System.out.print("Sorted Array:   ");
        for(int j=0; j<size; j++)
            System.out.print(linkArray[j].dData + " ");
        System.out.println("");
    }

    private static void testSortedList() {
        SortedList theSortedList = new SortedList();
        theSortedList.insert(20);    // insert 2 items
        theSortedList.insert(40);

        theSortedList.displayList(); // display list

        theSortedList.insert(10);    // insert 3 more items
        theSortedList.insert(30);
        theSortedList.insert(50);

        theSortedList.displayList(); // display list

        theSortedList.remove();      // remove an item

        theSortedList.displayList(); // display list
    }

    private static void testFirstLastList() {
        FirstLastList theList = new FirstLastList();

        theList.insertFirst(22);       // insert at front
        theList.insertFirst(44);
        theList.insertFirst(66);

        theList.insertLast(11);        // insert at rear
        theList.insertLast(33);
        theList.insertLast(55);

        theList.displayList();         // display the list

        theList.deleteFirst();         // delete first two items
        theList.deleteFirst();

        theList.displayList();         // display again
    }

    private static void testLinklist2() {
        Linklist2 theList = new Linklist2();  // make list

        theList.insertFirst(22, 2.99);      // insert 4 items
        theList.insertFirst(44, 4.99);
        theList.insertFirst(66, 6.99);
        theList.insertFirst(88, 8.99);

        theList.displayList();              // display list

        Link f = theList.find(44);          // find item
        if( f != null)
            System.out.println("Found link with key " + f.iData);
        else
            System.out.println("Can't find link");

        Link d = theList.delete(66);        // delete item
        if( d != null )
            System.out.println("Deleted link with key " + d.iData);
        else
            System.out.println("Can't delete link");

        theList.displayList();              // display list
    }

    private static void testLinklist() {
        Linklist theList = new Linklist();  // make new list

        theList.insertFirst(22, 2.99);      // insert four items
        theList.insertFirst(44, 4.99);
        theList.insertFirst(66, 6.99);
        theList.insertFirst(88, 8.99);

        theList.displayList();              // display list

        while( !theList.isEmpty() )         // until it's empty,
        {
            Link aLink = theList.deleteFirst();   // delete link
            System.out.print("Deleted ");         // display it
            aLink.displayLink();
            System.out.println("");
        }
        theList.displayList();              // display list
    }
}
