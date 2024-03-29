package com.eussi.ch11_hash;

import com.eussi.data._11.ChainHashTable;
import com.eussi.data._11.DataItem;
import com.eussi.data._11.HashTable;

import java.util.Arrays;

import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2020-02-28 16:56
 * @description
 */
public class Hash {
    /**
     *      哈希表是一种数据结构,它可以提供快速的插入操作和查找操作。第一次接触哈希表时,它
     * 的优点多得让人难以置信。不论哈希表中有多少数据,插入和删除(有时包括删除)只需要接近常量
     * 的时间:即O(1)的时间级。实际上,这只需要几条机器指令。
     *      对哈希表的使用者—人来说,这是一瞬间的事。哈希表运算得非常快,在计算机程序中,如
     * 果需要在一秒种内查找上千条记录,通常使用哈希表(例如拼写检查器)。哈希表的速度明显比树
     * 快,正如前面几章看到的,树的操作通常需要O(N)的时间级。哈希表不仅速度快,编程实现也相对
     * 容易。
     *      哈希表也有一些缺点:它是基于数组的,数组创建后难于扩展。某些哈希表被基本填满时,性
     * 能下降得非常严重,所以程序员必须要清楚表中将要存储多少数据(或者准备好定期地把数据转移
     * 到更大的哈希表中,这是个费时的过程)
     *      而且,也没有一种简便的方法可以以任何一种顺序(例如从小到大)遍历表中数据项。如果需
     * 要这种能力,就只能选择其他数据结构
     *      然而,如果不需要有序遍历数据,并且可以提前预测数据量的大小,那么哈希表在速度和易用
     * 性方面是无与伦比的。
     */
    public static void main(String[] args) {
        /**
         * 哈希化简介
         *      本节将要介绍哈希表和哈希化。其中一个重要的概念是如何把关键字转换成数组下标。在哈希
         * 表中,这个转换通过哈希函数来完成。然而,对于特定的关键字,并不需要哈希函数;关键字的值
         * 可以直接用于数组下标。先来看一个简单的例子;然后再逐步展示,若关键字值不能恰好用于数组
         * 下标时,如何使用哈希函数进行转换
         *
         * 雇员号码作为关键字
         *      假设现在要写一个程序,存取一个公司的雇员记录,这个小公司大约有1000个员工。每个雇
         * 员记录需要1000个字节的存储空间。因此,整个数据库的大小约1MB,一般的计算机内存都可以
         * 满足。
         *      计算机部门的领导已经强调必须要尽可能快地存取每个雇员记录。而且,每个雇员有一个特定
         * 的号码,从1(公司创立者)到1000(最近雇佣的工人)。这个雇员号码作为存取记录的关键字;
         * 事实上,用其他关键字进行存取完全没有必要。雇员很少被解雇,但是即使当他们不在公司了,他
         * 们的记录也要保存在数据库中以供参考(涉及到退休金等等问题)。在这种情况下需要使用什么数
         * 据结构呢?
         *
         * 关键字作为索引
         *      一种可能是用数组。每个雇员号码占数组的一个单元。单元的数组下标是当前记录的雇员号码。
         *      众所周知,如果知道数组下标,要访问特定的数组数据项非常方便。操作员要查找 Herman
         * Alcazar,他知道 Alcazar的雇员号码是72,所以他键入这个号码,程序直接检索数组下标为72的
         * 单元。总共只需要一条语句:
         *      empRecord rec = databaseArray[72]
         *      增加一个新项也非常快。只需要把它插到最后一个数据项的后面。下一个记录( Jim chan,是
         * 最新雇佣的员工,雇员号码是1001)将被放在第1001个单元。插入新记录也只需要一条语句
         *      databaseArray[totalEmployees++] = newRecord
         *      数组大概需要比当前的雇员数量多少大一些,为扩展留出空间,但不能指望可以大量扩展数组
         * 容量。
         *
         * 不总是如此有序
         *      使用基于数组的数据库,使得存储数据速度快且非常简单,这很吸引人。然而这个例子之所以
         * 能运行,仅仅是因为关键字组织得异乎寻常的好。关键字从1到一个已知的最大值,这个最大值对
         * 数组来说是合理的。没有删除,所以序列中不存在浪费内存的断裂带。新数据可以添加在数组的尾
         * 端,并且数组容量不需要比当前数据项的数量大很多
         *
         * 字典
         *      前面描述的雇员信息数据库的关键字“表现”得很好,而在许多应用中,情况并非如此。经典
         * 的例子是字典。如果想要把一本英文字典的每个单词,从a到 zyzzyva(这当然是一个单词),都写
         * 入计算机内存,以便快速读写,那么哈希表是一个不错的选择
         * 哈希表还在另一个类似的领域得到广泛应用,这就是高级计算机语言的编译器,它们通常用哈
         * 希表保留符号表。符号表记录了程序员声明的所有变量和函数名,以及它们在内存中的地址。程序
         * 需要快速地访问这些名字,所以哈希表是理想的数据结构
         *      例如,想在内存中存储5000个英文单词。起初可能考虑每个单词占据一个数组单元,那么数
         * 组的大小是50000同时可以使用数组下标存取单词。这样,存取确实很快。但是数组下标和单词
         * 有什么关系呢?例如给出一个单词 morphosis,怎么能找到它的数组下标呢
         *
         * 把单词转化成数组下标
         *      现在需要的是把单词转化成适当下标的系统。现在已经知道,计算机应用不同的编码方案
         * 用数字代表单个的字符。其中一种是ASCII编码,其中a是97,b是98,依此类推,直到122代
         * 表
         *      然而,ASCII码从0到255,可以容纳字母、标点等字符。英文单词中只有26个字母,所以
         * 可以设计出一种自己的编码方案,它可以潜在地存储内存空间。其中a是1,b是2,c是3,依
         * 此类推,直到26代表z。还要把空格用0代表,所以有27个字符。(这个字典中不使用大写字
         * 母。)
         *      如何把代表单个字母的数字组合成代表整个单词的数字呢?这有许多方法。下面看两种有代表
         * 性的方法,以及它们的优点和缺点。
         *
         * 把数字相加
         *      一个转换单词的简单方法是把单词每个字符的代码求和。例如把单词cats转换成数字。首先
         * 用前面创造的编码方案转换单词
         *      c=3
         *      a=1
         *      t=20
         *      s=19
         *      然后把它们相加
         *      3+1+20+19=43
         *      那么,在字典中,单词cats存储在数组下标为43的单元中。所有的英文单词都可以用这个办
         * 法转换成数组下标。
         *      这个方法工作得好吗?假设约定单词有十个字母。那么(记住空位是0)字典的第一个单词a
         * 的编码是
         *      0+0+0+0+0+0+0+0+0+1=1
         *      字典最后一个可能的单词是zzzzzzzzzz(10个z)。所有的字符编码的和是
         *      26+26+26+26+26+26+26+26+26+26=260
         *      因此,单词编码的范围是从1到260。不幸的是,词典中有50000个单词,所以没有足够的数
         * 组下标数来索引那么多的单词。每个数组数据项大概要存储192个单词(50000除以260)
         *      很显然,用一个单词占用一个数组单元的方案会发生问题。也许可以考虑每个数组数据项包含
         * 个子数组或链表。不幸的是,这个办法严重降低了存取速度。存取数据项确实很快,但是要在192
         * 个单词中找到其中一个,速度就很慢。
         *      所以,第一次把单词转化成数字的尝试还留下一些问题要解决,比如有太多的单词需要用同一
         * 个数组下标。(例如,was、tin、give、tend、moan、tick、bails、dredge和其他一百多个单词用编码
         * 方案求得的和都是43,和cats一样。)那么就得到这样的结论,这个方法没有把单词分得足够开,
         * 所以结果数组能表达的元素太少。需要扩展数组表达下标的空间。
         *
         * 幂的连乘
         *      我们来尝试一下用另外一种方法把单词映射成数字。如果数组容量太小,就确保它有足够的空
         * 间。如果创建一个数组,使得每个单词,实际上是每个潜在的单词,从a到zzzzzzzzzz都可以占
         * 据数组中一个单元的话,那么将会发生什么?
         *      如果要这么做,首先需要确定单词中的每个字符可以通过一种独一无二的方法得到最终的数
         * 字
         *      下面考虑一种用数字替换单词的类似方法。在一个大于10的数字中,每一数位代表用数字乘
         * 以从当前位到个位的位数那么多个10。那么7564的意思是
         *      7*1000+5*100+4*10+6*1
         *      或者写成10的幂的连乘
         *      7*10^3+5*10^2+4*10^1+6*10^0
         *      (在计算机程序中,输入例程应用一个类似的连乘和加法,把从键盘输入的数列转换成内存中
         * 的数。)
         *      在这个系统中,把数字拆成数列,用适当的10的幂乘以这些数位(因为有10个可能的数位)
         * 然后把乘积相加
         *      类似的方法,可以把单词分解成字母组合,把字母转化成它们的数字代码,乘以适当的27的
         * 幂(因为有27个可能的字符,包括空格),然后将结果相加。这就给出了每个单词对应的独一无二
         * 的数字
         *      例如要把单词cats转换成数字。首先像前面一样把数转化成数列,每个数字乘以相应的27的
         * 幂,然后将结果相加:
         *      3*27^3+1*27^2+20*27^1+19*27^0
         *      计算幂的值,得到
         *      3*19683+1*729+20*27+19*1
         *      字母代码与幂的值相乘,得到
         *      59049+729+540+19
         *      和为60337
         *      这个过程确实可以为每个可能的单词创建一个独一无二的整数。刚刚只是计算了一个4个字母
         * 的单词。较长的单词会发生什么?不幸的是,整数的范围会变得非常大。最长的10个字母的单词
         * zzzzzzzzzz,将转化成
         *      26*27^9+26*27^8+26*27^7+26*27^6+26*27^5+26*27^4+26*27^3+26*27^2+26*27^1+26*27^0
         *      仅27^9就超过7000000000000,所以可以看到结果非常巨大。在内存中的数组根本不可能有这
         * 么多的单元
         *      这个问题的出现是因为这个方案为每个可能的单词分配了一个数组单元,不管这个单词是不是
         * 真正的英语单词。因此数组单元就从aaaa,aaab, aaaaaaaaac,一直到zzzzzzzzzz,这些单元中只有
         * 一小部分存放了存在的英语单词,这是必需的,而大多数单元都是空的。
         *      第一种方案(把数字相加求和)产生的数组下标太少。第二种方案(与27的幂相乘并求和)
         * 产生的数组下标又太多。
         *
         * 哈希化
         *      现在需要一种压缩方法,把数位幂的连乘系统中得到的巨大的整数范围压缩到可接受的数组范
         * 围中。
         *      对于英语词典,多大的数组才合适?如果只有50000个单词,可能会假设这个数组大概就有这
         * 么多空间。但实际上,需要多一倍的空间容纳这些单词。(后面就会知道为什么这么说。)所以,最
         * 终需要容量为100000的数组
         *      现在,就找一种方法,把0到超过7000000000范围,压缩为从0到100000。有一种简单
         * 的方法是使用取余操作符,它的作用是得到一个数被另外一个数整除后的余数
         *      为了看到这个方法如何工作,首先来看在一个较小的,较易理解的数组范围如何运作。假设把
         * 从0到199的数字(用变量largeNumber代表),压缩为从0到9的数字(用变量smallNumber代表)。
         * 后者有10个数,所以说变量 smallRange值为10。而变量 largeNumber的值是多少并不重要(除非
         * 它超过编程语言规定的变量大小)。这个转换的Java表达式为
         *      smallNumber = largeNumber % smallRange;
         *      当一个数被10整除时,余数一定在0到9之间:例如,13%10为3,157%10为7。这样,就把0~199的
         * 范围压缩到0~9的范围,压缩率为20:1
         *      也可以用类似的方法把表示单词的惟一的数字压缩成数组的下标
         *      arrayIndex = hugeNumber % arraysize;
         *      这就是一种哈希函数。它把一个大范围的数字哈希(转化)成一个小范围的数字。这个小的范
         * 围对应着数组的下标。使用哈希函数向数组插入数据后,这个数组就称为哈希表。(本章稍后部分
         * 将会详细介绍哈希函数的设计。)
         *      回忆一下:通过把单词每个字母乘以27的适当次幂,使单词成为了一个巨大的数字
         *      hugeNumber=ch0*27^9+ch1*27^8+ch2*27^7+ch3*27^6+ch4*27^5+ch5*27^4+ch6*27^3+ch7*27^2+
         * ch8*27^1+ch9*27^0
         *      然后,使用取余操作符(%),把得到的巨大整数范围转换成两倍于要存储内容的数组下标范
         * 围。下面是哈希函数的例子:
         *      arraysize=numberWords * 2;
         *      arrayIndex = hugeNumber % arraysize;
         *      在原始的范围中,每个数字代表一个潜在的数据项(一种字母组合),但是它们中间只有很少一
         * 部分代表真实数据(英语单词)。哈希函数把这个巨大的整数范围转换成小的多的数组的下标范
         * 围。期望的数组应该有这样的特点,平均起来,每两个数组单元,就有一个单词。有些单元没有单
         * 词:而有些有多个单词
         *      这个方案的实际实现会遇到问题,因为 hugeNumber可能会超出变量范围,即使变量类型为long
         * 后面会看到如何处理这个问题
         *
         * 冲突
         *      把巨大的数字空间压缩成较小的数字空间,必然要付出代价,即不能保证,每个单词都映射到
         * 数组的空白单元。
         *      这个情况和把字符代码相加时的情况类似,但是现在更麻烦。当把字符代码相加时,只有约260
         * 种可能(对于最多只有10个字符的单词)。现在,把这个可能性扩展到了50000种。
         *      虽然如此,不可能避免把几个不同的单词哈希化到同一个数组单元,至少偶尔会这样。当然希
         * 望每个数组下标对应一个数据项,但是通常这不可能。只能寄希望于没有太多的单词有同样的数组
         * 下标
         *      假设要在数组中插入单词 melioration。通过哈希函数得到了它的数组下标后,发现那个单元已
         * 经有一个单词( demystify)了,因为这个单词哈希化后得到的数组下标与 melioration相同(对一个特
         * 定大小的数组),这种情况称为冲突。
         *      冲突的可能性会导致哈希化方案无法实施,实际上,可以通过另外的方式解泱这个问题。记住,前面
         * 已经说过,指定的数组大小两倍于需要存储的数据量。因此可能一半的单元是空的。当冲突发生时,一个方
         * 法是通过系统的方法找到数组的一个空位,并把这个单词填入,而不再用哈希函数得到的数组下标。这个方
         * 法叫做开放地址法。例如,如果cats哈希化的结果是5421,但它的位置已经被 parsnip占用,那么可能会考
         * 虑把cats放在5422的位置上。
         *      第二种方法(前面提到过)是创建一个存放单词链表的数组,数组内不直接存储单词。这样,当发生冲突
         * 时,新的数据项直接接到这个数组下标所指的链表中。这种方法叫做链地址法。
         *      剩余部分将讨论开放地址法和链地址法,最后回到哈希函数的问题
         *      迄今为止,一直都在关注如何哈希化字符串。这是很现实的,因为许多哈希表用来存储字符串
         * 然而,也有不少哈希表是存储数字的,例如前面提到的雇员号码的例子。在下面的讨论,,都使用数字(而
         * 不是字符串)作为关键字。这样事情容易理解,编程也会变得容易。然而,应该记住在许多情况中,这些数
         * 字是从字符串中得到的。
         *
         * 开放地址法
         *      在开放地址法中,若数据不能直接放在由哈希函数计算出来的数组下标所指的单元时,就要寻
         * 找数组的其他位置。下面要探索开放地址法的三种方法,它们在找下一个空白单元时使用的方法不
         * 同。这三种方法分别是线性探测、二次探测和再哈希法。
         *
         * 线性探测
         *      在线性探测中,线性地查找空白单元。如果5421是要插入数据的位置,它已经被占用了,那
         * 么就使用5422,然后是5423,依此类推,数组下标一直递增,直到找到空位。这就叫做线性探测
         * 因为它沿着数组的下标一步一步顺序地查找空白单元
         *  - 创建
         *      创建指定大小的哈希表，在通用的哈希表中,数组的大小应该是素数,例如，59是个选择
         *  - 填充
         *      当数据项数目占哈希表长的一半,或最多到三分之二(60个单元的表容纳40个数据项)时,
         * 哈希表的性能最好。并且哈希表填充时，已填充的单元的分布并不均匀。有时有一串空白单元,有时
         * 又有一串已填充单元
         *      哈希表中,一串连续的已填充单元叫做填充序列。增加越来越多的数据项时,填充序列变的越
         * 来越长。这叫做聚集。
         *      注意,如果把哈希表填得太满(例如,在60个单元的表中试图容纳59个数据项),那么在表中每填
         * 入一个数据项都要花费很长时间。可能会认为程序已经停止，但是请忍耐,哈希表在几乎被填满的数组
         * 中添加数据项,效率的确非常低
         *      而且,注意如果哈希表被完全填满,算法就会停止工作;
         *  - 查找
         *      对要查找的关键字值应用哈希函数。结果是一个数组下标。这个下标所指单元可能是要寻找的
         * 关键字;这是最好的情况,并且立即报告查找成功。然而,也可能这个单元被其他关键字占据。这就是冲突。
         * 根据冲突的位置,査找算法依次査找下一个单元。查找合适单元的过程叫做探测。
         *      根据冲突的位置,查找算法只是沿着数组一个一个地察看每个单元。如果在找到要寻找的关键字前
         * 遇到一个空位,说明查找失败。不需要再做查找,因为插入算法本应该把这个数据项插在那个空位上(如果
         * 不是前面那样的话)。
         *  - 插入
         *      插入算法使用和查找算法同样的算法找到合适的单元。如果最初的单元被占用,它会用线性探索,寻找
         * 空白单元。当找到后就插入数据。
         *      大多数数据项会一次插入成功,但有些会遇到冲突,需要沿数组步进,查找新的空白单元。它们走过的步
         * 数成为探索长度。大多数探索长度只有几个单元长。然而,有时会达到四个或五个单元长,当数组过分满的时
         * 候,探索长度可能更长
         *      注意哪些关键字映射到了相同的下标。如果数组大小是60,那么关键字7,67,127,187,247等等,一直
         * 到967都映射到7。试着插入这个序列或类似的序列。这样的序列证明了线性探测方法
         *  - 删除
         *      删除一个数据项不是简单地把某个单元的数据项删除,把它变成空白。为什么呢?记住,在插入操作中,探
         * 测过程走过一系列单元,查找一个空白单元。如果在一个填充序列的中间有一个空位,查找算法就会在看到它时
         * 中途放弃查找,即使最终本可以到达要求的那个单元
         *      因此,要用一个有特殊关键字值的数据项替代要被删除的数据项,以此标识此处的数据已不存在。如果假设
         * 所有有效的关键字值都是正数,则被删除的数据可以设置成-1。
         *      此时插入将在第一个有效空位或值为-1的位置插入一个新数据项。查找会把值为-1的数据项作为一个已存
         * 在的项,为的是可以跨过这项,查找更多的数据项。
         *      如果做了很多次删除操作;哈希表就会充满值为-1的数据项,这使得哈希表效率下降。因此许多哈希表不允
         * 许删除操作。如果实现了删除,应该尽量有节制地使用删除。
         *
         * 允许有重复的值吗?
         *      在哈希表中允许相同的数据项吗?如果填充过程不允许有相同关键字的数据项,但是如果要插入这样的数据,
         * 那么,会发现只有第一个数据可以被存取。存取第二个数据的惟一方法是删除第一个数据。这样做太不方便。
         *      可以重写查找算法,使它可以找到所有具有相同关键字的项,而不是只找第一个。然而,这需要搜索它遇到的
         * 每个线性序列。即使不存在重复关键字,由于要搜索整个表,所以非常耗时。在大多数情况下,可能会禁止重复的关
         * 键字。
         *
         * 聚集
         *      当哈希表变得越来越满时,聚集变得越来越严重。这导致产生非常长的探测长度。意味着存取序列最后的单元
         * 会非常耗时。
         *      数组填得越满,聚集越可能发生。数组有一半数据项时,这通常不是问题,当三分之二满的时候,情况也不会太
         * 坏。然而,如果超过这个界限,随着聚集越来越严重,性能下降也很严重。因此,设计哈希表的关键是确保它不会超过
         * 整个数组容量的一半,最多到三分之二。(在本章最后将讨哈希表的装填数据的程度和探测长度的数学关系。)
         */
        testHashTable(HashTable.FuncEnum.linear);
        sep();

        /**
         * 扩展数组
         *      当哈希表变得太满时,一个选择是扩展数组。在Java中,数组有固定的大小,而且不能扩展。
         * 编程时只能另外创建一个新的更大的数组,然后把旧数组的所有内容插入到新的数组中
         *      记住,哈希函数根据数组大小计算给定数据项的位置,所以,这些数据项不能再放在新数组中
         * 和老数组相同的位置上。因此,不能简单地从一个数组向另一个数组拷贝数据。需要按顺序遍历老
         * 数组,用 insert()方法向新数组中插入每个数据项。这叫作重新哈希化。这是一个耗时的过程,但如
         * 果数组要进行扩展,这个过程就是必要的。
         *      扩展后的数组容量通常是原来的两倍。实际上,因为数组容量应该是一个质数,所以新数组要
         * 比两倍的容量多一点。计算新数组的容量是重新哈希化的一部分。
         *      下面是帮助找到新数组容量(或原始的数组容量,因为可能会怀疑用户没有选择一个质数,这
         * 种情况经常发生)的几个例程。
         *      例程最终不会混合。例如,在 getPrime(方法中,可以检查2,然后只检查奇数,而不是每个数
         * 字。然而,这种优化没有多少用,因为只需检查几个数字,就会找到一个质数
         *      Java提供了 Vector类,这个类似数组的数据结构的类可以扩展。然而,它没有太大帮助,因为
         * 数组容量改变后需要重新哈希化所有数据项。
         */
        println("大于100的第一个质数是：");
        println(getPrime(100));
        int n = 150;
        println(n + "以内质数是：");
        findPrime(n);
        sep();

        /**
         * 二次探测
         *      前面已经看到,在开放地址法的线性探测中会发生聚集。一旦聚集形成,它会变得越来越大
         * 那些哈希化后的落在聚集范围内的数据项,都要一步一步移动,并且插在聚集的最后,因此使聚集
         * 变得更大。聚集越大,它增长得也越快
         *      这就像人群,当某个人在商场晕倒,人群就慢慢聚集。最初的人聚过来是因为看到了那个倒下
         * 的人;后面的人聚过来因为他们想知道每个人都在看什么。人群聚得越大,吸引的人就会越多
         * 已填入哈希表的数据项和表长的比率叫做装填因子。有1000单元的哈希表填入667个数
         * 据后,它的装填因子是2/3。
         *      loadFactor = nItems / arraySize;
         *      当装填因子不太大时,聚集分布得比较连贯。哈希表的某个部分可能包含大量的聚集,而另
         * 个部分还很稀疏。聚集降低了哈希表的性能。
         *      二次探测是防止聚集产生的一种尝试。思想是探测相隔较远的单元,而不是和原始位置相邻的
         * 单元
         *  - 步骤是步数的平方
         *      在线性探测中,如果哈希函数计算的原始下标是x,线性探测就是x+1,x+2,x+3,依此类推。
         * 而在二次探测中,探测的过程是x+1,x+4,x+9,x+16,x+25,依此类推。到原始位置的距离是步数的
         * 平方:x+1^2,x+2^2,x+3^2,x+4^2,x+5^2,等等。
         *      当二次探测的搜索变长时,好像它变得越来越绝望。第一次,它查找相邻的单元。如果这个单
         * 元被占用,它认为这里可能有一个小的聚集,所以,它尝试距离为4的单元。如果这里也被占用
         * 它变得有些焦虑,认为这里有一个大的聚集,然后尝试距离为9的单元。如果这里还被占用,它感
         * 到一丝恐慌,跳到距离为16的单元。很快,它会歇斯底里地飞跃整个数组空间。当哈希表几乎填
         * 满时,就会出现这种情况
         *  - 提示
         *      重要:数组容量总是选一个质数。例如用59代替60。(小于60的质数还有53,47,43,41,
         * 37,31,29,23,19,17,13,11,7,5,3和2。)如果数组容量不是质数,在探测过程中
         * 步长序列就会变得无限长。
         *  - 二次探测的问题
         *      二次探测消除了在线性探测中产生的聚集问题,这种聚集问题叫做原始聚集。然而,二次探测
         * 生了另外一种,更细的聚集问题。之所以会发生,是因为所有映射到同一个位置的关键字在寻找
         * 空位时,探测的单元都是一样的
         *      如将184,302,420和544依次插入到表中,它们都映射到7。那么302需要以一为步长的
         * 探测,420需要以四为步长的探测,544需要以九为步长的探测。只要有一项,其关键字映射到7,
         * 就需要更长步长的探测。这个现象叫做二次聚集。
         *      二次聚集不是一个严重的问题,但是,二次探测不会经常使用,因为还有稍微好些的解决方
         * 案。
         *
         * 再哈希法
         *      为了消除原始聚集和二次聚集,可以使用另外的一个方法:再哈希法。二次聚集产生的原因是
         * 二次探测的算法产生的探测序列步长总是固定的:1,4,9,16,依此类推
         *      现在需要的一种方法是产生一种依赖关键字的探测序列,而不是每个关键字都一样。那么,不
         * 同的关键字即使映射到相同的数组下标,也可以使用不同的探测序列。
         *      方法是把关键字用不同的哈希函数再做一遍哈希化,用这个结果作为步长。对指定的关键字
         * 步长在整个探测中是不变的,不过不同的关键字使用不同的步长。
         *      经验说明,第二个哈希函数必须具备如下特点
         *       - 和第一个哈希函数不同
         *       - 不能输出0〈否则,将没有步长;每次探测都是原地踏步,算法将陷入死循环)
         *      专家们已经发现下面形式的哈希函数工作得非常好:
         *          stepSize= constant - (key % constant );
         *      其中, constant是质数,且小于数组容量。例如,
         *          stepSize=5 - (key % 5);
         */
        testHashTable(HashTable.FuncEnum.rehash);
        sep();

        /**
         *  - 表的容量是一个质数
         *      再哈希法要求哈希表的容量是一个质数。为了考察为什么会有这个限制,假设表的容量不是质
         * 数。例如,假设表长是15(下标从0到14),有一个特定关键字映射到0,步长为5。探测序列是0,
         * 5,10,0,5,10,依此类推,一直循环下去。算法只尝试这三个单元,所以不可能找到某些空白
         * 单元,例如位置1,2,3或其他位置。算法最终会导致崩溃
         *      如果数组容量是13,即一个质数,探测序列最终会访问所有单元。即0,5,10,2,7,12,4,
         * 9,1,6,11,3,一直下去。只要表中有一个空位,就可以探测到它。用质数作为数组容量使得任
         * 何数想整除它都是不可能的,因此探测序列最终会捡査所有单元。
         *      类似的影响在二次探测中也存在。然而,由于每步的步长都在变化,且最终会超出变量的范围,
         * 所以避免了无限的循环
         *
         *      使用开放地址策略时,探测序列通常用再哈希法生成
         *
         * 链地址法
         *      开放地址法中,通过在哈希表中再寻找一个空位解决冲突问题。另一个方法是在哈希表每个单
         * 元中设置链表。某个数据项的关键字值还是像通常一样映射到哈希表的单元,而数据项本身插入到
         * 这个单元的链表中。其他同样映射到这个位置的数据项只需要加到链表中;不需要在原始的数组中
         * 寻找空位。
         *      链地址法在概念上比开放地址法中的几种探测策略要简单。然而，代码会比其他的长，因为必须
         * 要包含链表机制。这就要在程序中增加一个类。
         *
         * 装填因子
         *      链地址法中的装填因子(数据项数和哈希表容量的比值)与开放地址法的不同。在链地址法中,
         * 需要在有N个单元的数组中装入N个或更多的数据项;因此,装填因子一般为1,或比1大。这没
         * 有问题;因为,某些位置包含的链表中包含两个或两个以上的数据项
         *      当然,如果链表中有许多项,存取时间就会变长,因为存取特定数据项平均需要搜索链表的
         * 一半的数据项。找到初始的单元需要O(1)的时间级,而搜索链表的时间与M成正比,M为链表包含
         * 的平均项数。即O(M)的时间级。因此,并不希望链表太满。
         *      当使用这种方法装填数据时，通常当装填因子为1时，在这种情况下,大约三分之一的单元
         * 是空白单元,三分之一的单元有一个数据项,三分之一有两个或更多的数据项
         *      在开放地址法中,当装填因子超过二分之一或三分之二后,性能下降得很快。在链地址法中
         * 装填因子可以达到1以上,且对性能影响不大。因此,链地址法是更健壮的机制,特别是当事先难
         * 以确定哈希表要存储多少数据时更是如此,
         *
         * 重复值
         *      这里允许重复,在填入过程中可以填重复出现的值。所有相同关键字值的项都放在同一链表中
         * 所以如果需要找到所有项,不管査找是否成功,都要搜索整个链表。这会使性能略微下降。
         *
         * 删除
         *      链地址法中,删除并没有像开放地址法那样的问题。算法找到正确的链表,从链表中删除数据
         * 项。因为不需要探测,无所谓链表中的某一项是否为空。
         *
         * 表的容量
         *      如果用链地址法的话,表容量是质数的要求就不像在二次探测和再哈希法中显得那么重要。这
         * 里没有探测,所以不需要担心探测会因为表容量能被步长整除,而陷入无限的循环中
         *      但是,当数组容量不是质数时,这种关键字的分布还是能够导致数据的聚集。在讨论哈希函数
         * 的时候还会谈到这个问题。
         *
         * 桶
         *      另一种方法类似于链地址法,它在哈希表的每个单元中使用数组,而不是链表。这样的数组
         * 时称为桶。然而,这个方法不如链表有效,因为桶容量不好选择。如果容量太小,可能会溢出。如
         * 果太大,又浪费空间。链表是动态分配的,所以没有这个问题。
         */
        testChainHashTable();

        /**
         * 哈希函数
         *      本节中将会讨论一下设计好的哈希函数所需的因素,并看看是否可以改进本章前面提出的哈希
         * 化字符串的方法
         *
         * 快速的计算
         *      好的哈希函数很简单,所以它能快速计算。哈希表的主要优点是它的速度。如果哈希函数运行
         * 缓慢,速度就会降低。哈希函数中有许多乘法和除法是不可取的。(Java或C+语言中有位操作,
         * 例如,除以2的倍数,使得每位都向右移动,这种操作有时很有用。)
         *      哈希函数的目的是得到关键字值的范围,把它用一种方式转化成数组的下标值,这种方法应该
         * 使关键字值随机地分布在整个哈希表中。关键字可能完全随机,但也有可能不那么完全随机
         *
         * 随机关键字
         *      所谓完美的哈希函数把每个关键字都映射到表中不同的位置。只有在关键字组织得异乎寻常的
         * 好,且它的范围足够小,可以直接用于数组下标(例如本章开头提到的雇员号码的例子)的时候
         * 这种情况才可能出现
         *      大多数情况下,这种情况不会发生,哈希函数需要把较大的关键字值范围压缩成较小的数组下
         * 标的范围。
         *      在特定的数据库中,关键字值的分布决定哈希函数需要做什么。在本章,假设要数据随机地分
         * 布在整个表中。这时,哈希函数
         *      index = key % arraysize
         *      是令人满意的。它只包含一个数学操作,如果,关键字真是随机的,得到的下标也是随机的
         * 就会有良好的分布情况。
         *
         * 非随机关键字
         *      然而,数据通常不是随机分布的。假设数据库使用汽车零件号码作为关键字。也许这些号码是
         * 这样的形式
         *      033-400-03-94-05-0-535
         *      下面是各个数字的意义:
         *          0-2位:厂商号码(I到99,目前最大到70)
         *          3-5位:分类代号(100,150,200,250,一直到850)
         *          67位:引进的月份(1到12)
         *          8-9位:引进的年份(00到99)
         *          10-11位:序列号(1到99,不可能超过100)
         *          12位:是否有毒标志(0或1)
         *          13-15位:校验和(其他字段的和,对100取模)
         *      用作零件号的关键字可能是0334000394050535。然而,这样的关键字不是随机分布的。从0到
         * 999999大部分数实际不可能出现(例如,大于70的供应商号码,不是50倍数的分
         * 类代号,以及从13到99的月份)。而且,校验和并不独立于其他数字。对这些零件号码还需要做
         * 一些工作,以确保它们形成一个更靠近随机数分布的范围。
         *
         * 不要使用无用数据
         *      压缩关键字字段时,要把每个位都计算在内。例如,分类代码应压缩到0到15之间。而且
         * 校验和应该舍弃,因为它没有提供任何有用的信息;在压缩中是多余的。各种调整位的技术都可以
         * 用来压缩关键字中的不同字段
         *
         * 使用所有的数据
         *      关键字的每个部分(除了无用数据,如前所述)都应该在哈希函数中有所反映。不要只使用头
         * 四位数字,或类似的删除其他位的情况。关键字中提供的数据越多,哈希化后,越可能覆盖整个下
         * 标范围
         *      有时,关键字的范围太大,超出了int和long类型的规定。马上会讲到哈希化字符串,将可以
         * 看到如何控制溢出。
         *      总结:关于哈希函数的窍门是找到既简单又快的哈希函数,而且要去掉关键字中的无用数据,
         * 并尽量使用所有的数据
         *
         * 使用质数作为取模的基数
         *      通常,哈希函数包含对数组容量的取模操作。前面已经看到,使用二次探测和再哈希法时,要
         * 求数组容量为质数是多么的重要。然而,如果关键字本身不是随机分布的,不论使用什么哈希化系
         * 统,都应该要求数组容量是质数。
         *      这个论述是正确的,因为如果许多关键字共享一个数组容量作为除数,它们会趋向于映射到相
         * 同的位置,这会导致聚集。使用质数,可以消除这种可能性。例如,如果在这个汽车零件系统的例
         * 子中,表容量是50的倍数,那么分类代码全都映射到是50倍数的数组下标。然而,如果使用质数
         * 例如53,可以保证关键字会较平均地映射到数组中。
         *      所以,应该仔细检查关键字,并修正哈希算法,删除任何关键字分布不规则的地方
         *
         * 哈希化字符串
         *      在本章的开头,已经看到了如何把短小的字符串转换成数字,方法是每个数位乘以对应的一个
         * 常数的幂。特别的,看到一个例子,即把四个字母的单词cats用下面的公式转化成一个数字
         *      key=3*27^3+1*27^2+20*27^1+19*27^0
         *      这个方法有一个令人满意的特性,它包含了字符串的所有可能。计算的值用通常的方法哈希化
         * 成数组下标
         *      index=(key) % arraySize;
         *      下面是哈希化字符串的Java代码:
         *          public static int hashFunc1 (String key) {
         *              int hashVal=0;
         *              int pow27 =1;
         *              for(int j=key.length(); j>=0;j--) {// right to left
         *                  int letter =key.charAt(j)-96; // get char code
         *                  hashVal += pow27 * letter;    // times power of 27
         *                  pow27 *= 27;
         *              }
         *              return hashVal % arraySize
         *          } // end hash Func1()
         *      循环从单词最右边的字母开始。如果有N个字母,就是从第N-1个字母单元开始。字母的数
         * 字代码放在变量 letter中;这个代码依据本章开头得到的编码系统(a=1,依此类推)。然后乘以27
         * 的若干次幂,对于第N-1个字母,就是一次幂,对于第N-2个字母就是二次幂,依此类推。
         *      hashFunc1()方法不如想像的那么有效。除了字符转换外,在循环中有两次相乘和一次相加。还
         * 可以用一种叫做 Horner方法( Horner是英国数学家,1773-1827)的数学恒等式取代乘法。这个方
         * 法的表述是,如下的表达式
         *          var4*n^4+var3*n^3+var2*n^2+var1*n^1+var0*n^0
         *      可以写成下面的形式
         *          (((var4*n + var3)*n + var2)*n + var1)*n+var0
         *      为了对这个等式求值,从最内侧的括号开始,逐渐向外运算。如果把它转化成Jaa代码,就得
         * 到下面的方法
         *          public static int hashFunc2(String key){
         *              int hashVal = key.charAt(0) - 96;
         *              for (int j=1; j<key.length(); j++) // left to right
         *                  int letter=key.charAt(j) - 96; // get char code
         *                  hashVal =  hashVal*27+letter;   // multiply and add
         *              }
         *              return hashVal % arraySize;
         *          }   // end hash Func2()
         *      下面从单词最左边的单词开始(有时比从右边开始更自然),这样每次在循环中只有一次乘法
         * 和一次加法(除了从字符串中提取出字符外)
         *      不幸的是, hashFunc2方法不能处理大于7个字符的字符串。更长的字符串会导致 hashVal的
         * 值超出int类型的范围。(如果使用long类型,在字符串太长的时候也会出现同样的问题)
         *      可以修正这种基本的方法避免变量溢出吗?注意,最后得到的关键字总比数组容量小,因为使
         * 用了取模操作。那么,这个超大的整数不是最终的数组下标:它只是中间结果
         *      由此可以得出结论,在 Horner公式的每一步中,都可以应用取模操作符(%)。这和最后只应
         * 用一次取模操作符的结果是一样,但是避免了溢出。(循环中增加一个操作。)下面是修正过的
         * hashFunc3()方法
         *          public static int hashFunc3 (String key) {
         *              int hashVal =0
         *              for(int j=0; j<key.length(); j++) {// left to right
         *                  int letter=key.charAt(j)-96; // get char code
         *                  hashVal =(hashVal * 27 + letter) % arraySize;
         *              }
         *              return hashVal;
         *          }
         *      这种方法或类似的方法通常用来哈希化字符串。也可以应用不同的位操作技巧,例如使用32
         * (或者更大的2的幂)作为模基取代27,这样,乘法可以结合右移操作符(>>)增加效率,右移操
         * 作比取模更快。
         *      可以使用这个方法把任意长度的字符串转换成适当的数字。字符串可以是单词、名字或其他字
         * 符的组合
         *
         * 折叠
         *      另一个哈希函数是把关键字的数字分成几组,然后几个组相加。这样做确保了所有数字对哈希
         * 值都有贡献。一个组拥有几个数字,是和数组容量相对应的。即,对于有1000项的数组,每组包
         * 含3个数字。
         *      例如,假设要哈希化一个9位数的社会安全号码,使用线性探测。如果数组容量是1000项
         * 就把9位数分成三组。如果某个号码是123-45-6789,然后按公式123+456+789=1368,计算关键字
         * 以使用取模操作符截短这个和,使得最大数组下标是999。在这里,1368%1000=368。如果数组
         * 容量是100,就需要把9位数分成4个两位数和1个一位数的组:12+34+56+78+9=189,然后189
         * 当数组容量是10的倍数时,容易考虑这个过程如何进行。然而,要得到最好的结果,它应该
         * 是质数,正如其他的哈希函数一样。这个方案的实现留作习题。
         *
         * 哈希化的效率
         *      已经注意到在哈希表中执行插入和搜索操作可以达到O(1)的时间级。如果没有发生冲突,只需
         * 要使用一次哈希函数和数组的引用,就可以插入一个新数据项或找到一个已存在的数据项。这是最
         * 小的存取时间级
         *      如果发生冲突,存取时间就依赖后来的探测长度。在一次探测中,每个单元的存取时间要加上
         * 寻找一个空白单元(插入时)或一个己存在单元的时间。在一次存取中,要检查这个单元是不是空
         * 的,以及(查找或删除时)这个单元是不是包含要寻找的数据项。
         *      因此,一次单独的查找或插入时间与探测的长度成正比。这里还要加上哈希函数的常量时间。
         * 平均探测长度(以及平均存取时间)取决于装填因子(表中项数和表长的比率)。随着装填因
         * 子变大,探测长度也越来越长
         *      下面会看到在不同的哈希表中,探测长度和装填因子之间的关系
         *
         * 开放地址法
         *      随着装填因子变大,效率下降的情况,在不同开放地址法方案中比链地址法中更严重。
         *      开放地址法中,不成功查找比成功的查找花费更多的时间。在探测序列中,只要找到要查找的
         * 数据项,算法就能停止,平均起来,这会发生在探测序列的一半。另一方面,要确定算法不能找到
         * 这样的项,就必须走过整个探测序列。
         *
         * 线性探测
         *      下面的等式显示了线性探测时,探测序列(P)和装填因子(L)的关系,对成功的查找来说
         *          P=(1+1/(1-L)^2)/2
         *      而对于不成功的查找来说
         *          P=(1+1/(1-L))/2
         *      这些公式来自 Knuth,把它们推导出来相当复杂。
         *      该公式表示，当装填因子为1/2时,成功的搜索需要1.5次比较,不成功的搜索需要2.5次。当装填
         * 因子为2/3时,分别需要2.0次和5.0次比较。如果装填因子更大,比较次数会变得非常大。
         *      正如我们看到的那样,应该使装填因子保持在2/3以下,最好在1/2以下。另一方面,装填因子越
         * 低,对于给定数量的数据项,就需要越多的空间。实际情况中,最好的装填因子取决于存储效率和速
         * 度之间的平衡。随着装填因了变小,存储效率下降,而速度上升。
         *
         * 二次探测和再哈希法
         *      二次探测和再哈希法的性能相当。它们的性能比线性探测略好。对成功的搜索,公式(仍然来自
         * Knuth)是
         *          -log2((1-loadFactor) / loadFactor)
         *      对于不成功的查找,公式是
         *          1/(1-loadFactor)
         *      该公式表示，当装填因子为0.5时,成功和不成功的查找都平均需要两
         * 次比较。当装填因子为2/3时,分别需要2.37次和3.0次比较。当装填因子为0.8时,分别需要
         * 2.9和5.0次。因此,对于较高的装填因子,对比线性探测,二次探测和再哈希法还是可以忍受
         * 的。
         *
         * 链地址法
         *      链地址法的效率分析有些不同,一般来说比开放地址法简单
         *      我们想知道要查找或插入一个新项需要多少时间。假设这个操作的最耗费时间的部分是比较待
         * 查找项和表中其他项的关键字。同时假设,哈希函数执行的时间和判断是否到达表尾的时间与一次
         * 关键字比较的时间相等。因此,所有的操作需要1+ nComps的时间,其中, nComps是关键字比较
         * 次数。
         *      假设哈希表包含 arraySize个数据项,每个数据项有一个链表,在表中有N个数据项。那么
         * 平均起来每个链表有N除arraySize个数据项
         *          AverageListLength = N / arraySize
         *      这和装填因子的定义是相同的
         *          loadFactor =N / arraySize
         *      所以平均表长等于装填因子。
         *  - 查找
         *      在成功的査找中,算法映射到正确的链表,然后在链表中查找数据项。平均起来,找到正确项
         * 之前,要检查一半的数据项。因此,查找时间是
         *          1+ loadFactor / 2
         *      不管链表是否有序,都遵循这个公式。在不成功的查找中,如果链表不是有序的,要检查所有
         * 的数据项,所以时间是
         *          1 + loadFactor
         *      对于有序表,在不成功查找中只需要检查一半的数据项,所以时间与成功查找的时间相同。
         * 在链地址法中,通常装填因子为1(数据项的个数和数组容量相同)。较小的装填因子不能显著地
         * 提升性能,但是,所有操作的时间都会随着装填因子的变大而增长,所以,不宜把装填因子提升到2
         * - 插入
         *      如果链表是无序的,插入操作是立即完成的,这里不需要比较。哈希函数必须计算,所以插入
         * 的时间是1
         *      如果链表有序,那么,由于存在不成功的查找,平均要检查一半的数据项,所以插入的时间是
         *          1 + loadFactor / 2
         *
         * 开放地址法和链地址法的比较
         *      如果使用开放地址法,对于小型的哈希表,再哈希法似乎比二次探测的效果好。有一个情况例
         * 外,就是内存充足,并且哈希表一经创建,就不再改变其容量:在这种情况下,线性探测相对容易
         * 实现,并且,如果装填因子低于0.5,几乎没有什么性能下降
         *      如果在哈希表创建时,要填入的项数未知,链地址法要好过开放地址法。如果用开放地址法,
         * 随着装填因子变大,性能会下降很快,但是用链地址法,性能只能线性地下降
         *      当两者都可选时,使用链地址法。它需要使用链表类,但回报是增加比预期更多的数据时,不
         * 会导致性能的快速下降。
         *
         * 哈希化和外部存储
         *      在第10章的最后,讨论了使用B-树作为外部(基于磁盘)存储的数据结构。下面简要看一下
         * 哈希表用作外部存储的情况。
         *      回忆一下第10章,磁盘由许多包含文件的“块”组成,存取一个块的时间比任何在内存中存
         * 取数据的时间都要长得多。因此,在设计外部存储策略时,不考虑最小化存取块的数量。
         *      另一方面,外部存储器单位容量相当便宜。所以,可以使用更大数量的存储器,超过要存储的
         * 项数,如果这样的话,会加快存取时间。这就有可能使用哈希表。
         *
         * 文件指针表
         *      外部哈希化的关键部分是一个哈希表,它包含块成员,指向外部存储器中的块。哈希表有时叫
         * 做索引(就像书的索引)。它可以存储在内存中,如果它太大,也可以存储在磁盘上,而把它的
         * 部分放在内存中。即使把哈希表都放在内存中,也要在磁盘中保存一个备份,文件打开时,把它读
         * 入内存。
         *
         * 未填满块
         *      这里还要使用一下第10章的例子。在这个例子中,块大小为8192字节,一个记录为512字节。
         * 因此,一个块可容纳16个记录。哈希表的每个条目指向某个块。假设一个特殊的文件有100个块。
         *      内存中的索引(哈希表)记录了文件块的指针,第一个块为0,一直到99。
         *      在外部哈希化中,重要的是块不要填满。因此,每个块平均存储8个记录。有的块多些,有的
         * 少些。在文件中大概有800个记录。
         *      所有关键字映射为同一个值的记录都定位到相同块。为找到特定关键字的一个记录,搜索算法
         * 哈希化关键字,用哈希值作为哈希表的下标,得到某个下标中的块号,然后读取这个块
         *      这个过程是有效的,因为定位一个特定的数据项,只需要访问一次块。缺点是相当多的磁盘空
         * 间被浪费了,因为设计时规定,块是不允许填满的
         *      为了实现这个方案,必须仔细选择哈希函数和哈希表的大小,为的是限制映射到相同的值关键
         * 字的数量。在本例中,平均每个值只对应8个记录
         *
         * 填满的块
         *      即使用一个好的哈希函数,块偶尔也会填满。这时,可以使用在内部哈希表中讨论的处理冲突
         * 的不同方法:开放地址法和链地址法
         *      在开放地址法中,插入时,如果发现一个块是满的,算法在相邻的块插入新记录。在线性探测
         * 中,这是下一个块,但也可以用二次探测或再哈希法选择。在链地址法中,有一个溢出块;当发现
         * 块已满时,新记录插在溢出块中。
         *      填满的块是不合需要的,因为有了它就需要额外的磁盘访问,这就需要两倍的访问时间。然而,
         * 如果这种情况不经常发生,也可以接受
         *      这里只讨论了用于外部存储的最简单的哈希表实现。还有很多更复杂的方法,已经超出了本书
         * 的范围。
         *
         */

        /**
         * 小结
         * - 哈希表基于数组
         * - 关键字值的范围通常比数组容量大
         * - 关键字值通过哈希函数映射为数组的下标。
         * - 英文字典是一个数据库的典型例子,它可以有效的用哈希表来处理。
         * - 一个关键字哈希化到已占用的数组单元,这种情况叫做冲突。
         * - 冲突可以用两种方法解决:开放地址法和链地址法
         * - 在开放地址法中,把冲突的数据项放在数组的其他位置
         * - 在链地址法中,每个数组单元包含一个链表。把所有映射到同一个数组下标的数据项都插
         * 在这个链表中。
         * - 讨论了三种开发地址法:线性探测、二次探测和再哈希法
         * - 在线性探测中,步长总是1,所以如果x是哈希函数计算得到的数组下标,那么探测序列
         * 就是x,x+1,x+2,x+3,依此类推。
         * - 找到一个特定项需要经过的步数叫做探测长度。
         * - 在线性探测中,已填充单元的长度不断增加。它们叫做首次聚集,这会降低哈希表的性能。
         * - 在二次探测中,x的位移是步数的平方,所以探测序列就是x,x+1,x+4,x+9,x+16,依此
         * 类推。
         * - 二次探测消除了首次聚集,但是产生了二次聚集,它比首次聚集的危害略小
         * - 二次聚集的发生是因为所有映射到同一个单元的关键字,在探测过程中执行了相同的序
         * 列
         *  - 发生上述情况是因为步长只依赖于哈希值,与关键字无关。
         *  - 在再哈希法中,步长依赖于关键字,且从第二个哈希函数中得到。
         *  - 在再哈希法中,如果第二个哈希函数返回一个值s,那么探测序列就是x,x+s,x+2s,x+3s,
         *  x+4s,依此类推,这里s由关键字得到,但探测过程中保持常量。
         *  - 装填因子是表中数据项数和数组容量的比值。
         *  - 开放地址法中的最大装填因子应该在0.5附近,若具有相同的装填因子,对于再哈希法来
         *  说,查找的平均探测长度是2。
         *  - 在开放地址法中,当装填因子接近1时,查找时间趋于无限。
         *  - 在开放地址法中,关键是哈希表不能填得太满。
         *  - 对于链地址法,装填因子为1比较合适
         *  - 这时,成功的探测长度平均是15,不成功的是20。
         *  - 链地址法的探测长度随着装填因子变大而线性增长
         *  - 字符串可以这样哈希化,每个字符乘以常数的不同次幂,求和,然后用取模操作符(%)
         *  缩减结果,以适应哈希表的容量。
         *  - 如果在 Horner方法中用多项式表达哈希化,每一步中都应用取模操作符,以免发生溢出
         *  - 哈希表的容量通常是一个质数。这在二次探测和再哈希法中非常重要
         *  - 哈希表可用于外部存储,一种做法是用哈希表的单元存储磁盘文件的块号码。
         */
    }

    public static void testHashTable(HashTable.FuncEnum funcEnum) {
        int size = 11;
        int n = 4;
        int aKey, keysPerCell; //关键字范围比例
        // get sizes
        println("size of hash table: " + size + ", funcEnum: " + funcEnum.name());
        keysPerCell = 10;
        // make table
        HashTable theHashTable = new HashTable(size, funcEnum);
        theHashTable.displayTable();
        println();

        println("initial number of items: " + n);
        DataItem aDataItem;
        for(int j=0; j<n; j++) {
            aKey = (int)(Math.random() * keysPerCell * size);
            aDataItem = new DataItem(aKey);
            theHashTable.insert(aDataItem);
        }
        theHashTable.displayTable();
        println();

        for (Integer insertKey : Arrays.asList(11, 12, 45, 11)) {
            println("key value to insert: " + insertKey);
            aDataItem = new DataItem(insertKey);
            theHashTable.insert(aDataItem);
            theHashTable.displayTable();
            println();
        }

        int deleteKey = 45;
        println("key value to delete: " + deleteKey);
        theHashTable.delete(deleteKey);
        theHashTable.displayTable();
        println();

        for (Integer findKey : Arrays.asList(13, 11)) {
            println("key value to find: " + findKey);
            aDataItem = theHashTable.find(findKey);
            if(aDataItem != null)
                println("Found " + findKey);
            else
                println("Could not find " + findKey);
            println();
        }
    }

    public static void testChainHashTable() {
        int size = 11;
        int n = 4;
        int aKey, keysPerCell; //关键字范围比例
        // get sizes
        println("size of chain hash table: " + size);
        keysPerCell = 10;
        // make table
        ChainHashTable theHashTable = new ChainHashTable(size);
        theHashTable.displayTable();
        println();

        println("initial number of items: " + n);
        for(int j=0; j<n; j++) {
            aKey = (int)(Math.random() * keysPerCell * size);
            theHashTable.insert(aKey);
        }
        theHashTable.displayTable();
        println();

        for (Integer insertKey : Arrays.asList(11, 12, 45, 11)) {
            println("key value to insert: " + insertKey);
            theHashTable.insert(insertKey);
            theHashTable.displayTable();
            println();
        }

        int deleteKey = 45;
        println("key value to delete: " + deleteKey);
        theHashTable.delete(deleteKey);
        theHashTable.displayTable();
        println();

        for (Integer findKey : Arrays.asList(13, 11)) {
            println("key value to find: " + findKey);
            Integer item = theHashTable.find(findKey);
            if(item != null)
                println("Found " + findKey);
            else
                println("Could not find " + findKey);
            println();
        }
    }

    private static void findPrime(int n) {
        //质数是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数。
        for (int i=2; i<n; i++) {
            if(isPrime(i))
                print(i + " ");
        }
    }

    private static int getPrime(int min) {
        for(int j=min+1; true; j++) {
            if(isPrime(j))
                return j;
        }
    }

    private static boolean isPrime(int n) {
        for(int j=2; j*j<=n; j++)
            if(n%j==0)
                return false;
        return true;
    }


}
