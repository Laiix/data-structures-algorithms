package com.eussi.data._02;

import static com.eussi.util.PrintUtil.print;
import static com.eussi.util.PrintUtil.println;


/**
 * 有序数组
 */
public class OrderedArray {
    private long[] a; // ref to array a
    private int nElems; // number of data items

    public OrderedArray(int max) {
        a = new long[max]; // create array
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    /**
     * 二分法查找
     * @param searchKey
     * @return
     */
    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems-1;
        int cur;
        while(lowerBound<=upperBound) {
//            cur = (lowerBound + upperBound)/2;
            cur = (upperBound - lowerBound)/2 + lowerBound; //可防止溢出
            if(a[cur]==searchKey) {
                return cur;
            } else if(a[cur]<searchKey){
                lowerBound = cur + 1;
            } else if(a[cur]>searchKey) {
                upperBound = cur -1;
            }
        }
        return nElems;
    }

    /**
     * 通过递归的方式进行二分法查找
     * @param searchKey
     * @return
     */
    public int findByRecursion(long searchKey) {
        return recFind(searchKey, 0, nElems-1);
    }

    private int recFind(long searchKey,
                        int lowerBound,
                        int upperBound) {
        int curIn = (lowerBound + upperBound ) / 2;
        if(a[curIn]==searchKey)
            return curIn;              // found it
        else if(lowerBound > upperBound)
            return nElems;             // can't find it
        else {
            if(a[curIn] < searchKey)   // it's in upper half
                return recFind(searchKey, curIn+1, upperBound);
            else                       // it's in lower half
                return recFind(searchKey, lowerBound, curIn-1);
        }
    }

    /**
     * 线性查找插入
     * @param value
     */
    public void insert(long value) {
        int j;
        for (j = 0; j < nElems; j++)
            // find where it goes
            if (a[j] > value) // (linear search)
                break;
        for (int k = nElems; k > j; k--)
            // move bigger ones up
            a[k] = a[k - 1];
        a[j] = value; // insert it
        nElems++; // increment size
    }

    public void insert(long... values) {
        for (long value : values) {
            insert(value);
        }
    }


    /**
     * 二分法查找插入
     * @param value
     * @return
     */
    public boolean delete(long value) {
        int j = find(value); // delete()已经用到了二分查找
        if (j == nElems) // can't find it
            return false;
        else {
            for (int k = j; k < nElems; k++)
                // move bigger ones down
                a[k] = a[k + 1];
            nElems--; // decrement size
            return true;
        }
    } // end delete()

    /**
     * 二分法查找插入
     * @param value
     */
    public void insertByBinarySearch(long value) {
        int index = findInsertIndex(value);
        for (int k = nElems; k > index; k--)
            // move bigger ones up
            a[k] = a[k - 1];
        a[index] = value; // insert it
        nElems++; // increment size
    }

    /**
     * 通过二分法查找到数据应该插入的位置
     * @param searchKey
     * @return
     */
    public int findInsertIndex(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems-1;
        int cur;
        while(lowerBound<=upperBound) {
            cur = (upperBound - lowerBound)/2 + lowerBound; //可防止溢出
            if(a[cur]==searchKey) {
                return cur;
            } else if(a[cur]<searchKey){
                lowerBound = cur + 1;
            } else if(a[cur]>searchKey) {
                upperBound = cur -1;
            }
        }
        return lowerBound;
    }

    public void display() {
        print("[");
        for (int j = 0; j < nElems-1; j++)
            // for each element,
            print(a[j] + ", "); // display it
        print(a[nElems-1]);
        println("]");
    }

    /**
     * 合并两个数组，依然有序
     * @param orderArr
     * @return
     */
    public OrderedArray merge(OrderedArray orderArr) {
        // 假设数组空间总是足够
        OrderedArray dist = new OrderedArray(this.nElems + orderArr.nElems);
        for (int i = 0; i < orderArr.size(); i++) {
            dist.insert(orderArr.a[i]);
        }
        for (int i = 0; i < this.size(); i++) {
            dist.insert(this.a[i]);
        }
        return dist;
    }

    /**
     * 合并两个数组，依然有序
     * @param orderArr
     * @return
     */
    public OrderedArray merge2(OrderedArray orderArr) {
        // 假设数组空间总是足够
        int inElems = this.nElems;
        int jnElems = orderArr.nElems;
        int i = 0;
        int j = 0;
        OrderedArray dist = new OrderedArray(this.nElems + orderArr.nElems);
        while(i<inElems && j<jnElems) {
            if(this.a[i]<orderArr.a[j]) {
                dist.a[i+j] = a[i];
                i++;
            } else {
                dist.a[i+j] = orderArr.a[j];
                j++;
            }
        }

        while(i<inElems) {
            dist.a[i+j] = a[i];
            i++;
        }

        while(j<jnElems) {
            dist.a[i+j] = orderArr.a[j];
            j++;
        }

        dist.nElems = i + j;
        return dist;
    }

    /**
     * 合并两个数组，依然有序
     * @param orderArr
     * @return
     */
    public OrderedArray merge3(OrderedArray orderArr) {
        // 假设数组空间总是足够
        int inElems = this.nElems;
        int jnElems = orderArr.nElems;
        int i = 0;
        int j = 0;
        int z = 0;
        OrderedArray dist = new OrderedArray(this.nElems + orderArr.nElems);
        while(i<inElems && j<jnElems) {
            if(this.a[i]<orderArr.a[j]) {
                dist.a[z++] = a[i++];
            } else {
                dist.a[z++] = orderArr.a[j++];
            }
        }
        while(i<inElems) {
            dist.a[z++] = a[i++];
        }
        while(j<jnElems) {
            dist.a[z++] = orderArr.a[j++];
        }

        dist.nElems = z;
        return dist;
    }

}
