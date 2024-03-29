package com.eussi.ch11_hash;

import com.eussi.data._08.TreeNode;
import com.eussi.data._11.*;

import static com.eussi.util.PrintUtil.*;

/**
 * @author wangxueming
 * @create 2020-03-03 20:01
 * @description 编程作业
 */
public class Exercise {
    public static void main(String[] args) {
        /**
         * 11.1 修改HashTable.java程序，改用二次探测。
         */
        printExercise("11.1");
        exercise_1();

        /**
         * 11.2 实现用一个线性探测哈希表存储字符串。需要一个把字符串转换成数组下标的
         * 	 哈希函数。参考本章“哈希化字符串”一节。假设字符串全是小写字母，所以26
         * 	 个字符就足够了。
         */
        sepExercise("11.2");
        exercise_2();

        /**
         * 11.3 写一个哈希函数，实现数字折叠的方法（正如本章“哈希函数”一节描述的）。
         *	 程序应该可以适应任何数组容量和任何关键字长度。使用线性探测。存取某个
         *	 数中的一组数字比想像的要简单。如果数组容量不是10的倍数，有影响吗？
         * 11.4 为hash.java程序写一个rehash()方法。只要装填因子大于0.5，insert()方
         *	 法会调用它，把整个哈希表复制到比它大两倍的数组中，新的数组容量应该是
         *	 一个质数。参考本章“扩展数组”一节。不要忘记，必须处理已经“删除”的数据
         *	 项,那里的值为-1。
         */
        sepExercise("11.3/11.4");
        exercise_3();

        /**
         * 11.5 使用二叉搜索树，而不是使用地址法中的链表解决冲突。即创建一个树的数组
         *  作为哈希表：可以使用hashChain.java程序（清单11.3）和第8章tree.java
         *  程序（清单8.1）中的Tree类作为基础。为了显示这个基于树的哈希表，每棵
         *  使用中序遍历。
         *  树比链表好的地方是搜索只需要O(logN)的数量级,而不是o(N)。如果装填因子
         *  很大,省下的这部分时间是很可观的。在链表中,检査15个数据项最少用5次比较,
         *  而在树中只用4次
         *  重复项在树和哈希表中都会出现问题,所以要增加一些代码,防止在哈希表中插入
         *  重复项。(小心:Tree类中的find方法假设是一棵非空树。)为了缩短这个程序的
         *  清单,不用考虑删除,对树来说,删除需要大量的代码。
         */
        sepExercise("11.5");
        exercise_5();
    }

    private static void exercise_1() {
        Hash.testHashTable(HashTable.FuncEnum.quadratic);
    }

    private static void exercise_2() {
        int size = 11;
        int n = 4;
        String aKey;
        // get sizes
        println("size of string hash table: " + size);
        println("initial number of items: " + n);
        // make table
        StringHashTable theHashTable = new StringHashTable(size);
        theHashTable.displayTable();

        DataItem<String> aDataItem;
        for(int j=0; j<n; j++) {
            StringBuffer sb = new StringBuffer();
            int length = (int) (java.lang.Math.random() * 5 + 1);
            for (int i = 0; i < length; i++) {
                sb.append((char) (java.lang.Math.random() * 26 + 97));
            }
            aDataItem = new DataItem<>(sb.toString());
            theHashTable.insert(aDataItem);
        }

        theHashTable.displayTable();

        aKey = "aaa";
        println("key value to insert: " + aKey);
        aDataItem = new DataItem<>(aKey);
        theHashTable.insert(aDataItem);
        theHashTable.displayTable();

        aKey = "aaa";
        println("key value to delete: " + aKey);
        theHashTable.delete(aKey);
        theHashTable.displayTable();

        aKey = "bbb";
        println("key value to insert: " + aKey);
        aDataItem = new DataItem<>(aKey);
        theHashTable.insert(aDataItem);
        theHashTable.displayTable();

        aKey = "aaa";
        println("key value to insert: " + aKey);
        aDataItem = new DataItem<>(aKey);
        theHashTable.insert(aDataItem);
        theHashTable.displayTable();

        aKey = "ccc";
        println("key value to find: " + aKey);
        aDataItem = theHashTable.find(aKey);
        if(aDataItem != null) {
            println("Found " + aKey);
        }
        else
            println("Could not find " + aKey);


        aKey = "aaa";
        println("key value to find: " + aKey);
        aDataItem = theHashTable.find(aKey);
        if(aDataItem != null) {
            println("Found " + aKey);
        }
        else
            println("Could not find " + aKey);

    }


    private static void exercise_3() {
        int size = 11;
        int n = 6;
        int aKey, keysPerCell; //关键字范围比例
        // get sizes
        println("size of hash table: " + size);
        println("initial number of items: " + n);
        keysPerCell = 10;
        // make table
        ExerciseHashTable theHashTable = new ExerciseHashTable(size);
        theHashTable.displayTable();

        DataItem<Integer> aDataItem;
        for(int j=0; j<n; j++) {
            aKey = (int) (java.lang.Math.random() * keysPerCell * size);
            aDataItem = new DataItem<>(aKey);
            theHashTable.insert(aDataItem);
        }

        theHashTable.displayTable();

        aKey = 11;
        println("key value to insert: " + aKey);
        aDataItem = new DataItem<>(aKey);
        theHashTable.insert(aDataItem);
        theHashTable.displayTable();

        aKey = 11;
        println("key value to delete: " + aKey);
        theHashTable.delete(aKey);
        theHashTable.displayTable();

        aKey = 12;
        println("key value to insert: " + aKey);
        aDataItem = new DataItem<>(aKey);
        theHashTable.insert(aDataItem);
        theHashTable.displayTable();

        aKey = 11;
        println("key value to insert: " + aKey);
        aDataItem = new DataItem<>(aKey);
        theHashTable.insert(aDataItem);
        theHashTable.displayTable();

        aKey = 13;
        println("key value to find: " + aKey);
        aDataItem = theHashTable.find(aKey);
        if(aDataItem != null) {
            println("Found " + aKey);
        }
        else
            println("Could not find " + aKey);


        aKey = 11;
        println("key value to find: " + aKey);
        aDataItem = theHashTable.find(aKey);
        if(aDataItem != null) {
            println("Found " + aKey);
        }
        else
            println("Could not find " + aKey);

    }

    private static void exercise_5() {
        int size = 5;
        int n = 6;
        int aKey, keysPerCell; //关键字范围比例
        // get sizes
        println("size of hash table: " + size);
        println("initial number of items: " + n);
        keysPerCell = 10;
        // make table
        TreeChainHashTable theHashTable = new TreeChainHashTable(size);
        theHashTable.displayTable();

        for(int j=0; j<n; j++) {
            aKey = (int) (java.lang.Math.random() * keysPerCell * size);
            theHashTable.insert(aKey);
        }

        theHashTable.displayTable();

        aKey = 11;
        println("key value to insert: " + aKey);
        theHashTable.insert(aKey);
        theHashTable.displayTable();

        aKey = 11;
        println("key value to delete: " + aKey);
        theHashTable.delete(aKey);
        theHashTable.displayTable();

        aKey = 12;
        println("key value to insert: " + aKey);
        theHashTable.insert(aKey);
        theHashTable.displayTable();

        aKey = 11;
        println("key value to insert: " + aKey);
        theHashTable.insert(aKey);
        theHashTable.displayTable();

        aKey = 13;
        println("key value to find: " + aKey);
        TreeNode<Integer> findKey = theHashTable.find(aKey);
        if(findKey != null)
        {
            println("Found " + aKey);
        }
        else
            println("Could not find " + aKey);


        aKey = 11;
        println("key value to find: " + aKey);
        findKey = theHashTable.find(aKey);
        if(findKey != null)
        {
            println("Found " + aKey);
        }
        else
            println("Could not find " + aKey);

    }


}
