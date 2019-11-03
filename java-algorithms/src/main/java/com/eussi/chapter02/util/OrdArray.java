package com.eussi.chapter02.util;

// OrdArray.java
// demonstrates ordered array class
////////////////////////////////////////////////////////////////
public class OrdArray {
    private long[] a; // ref to array a
    private int nElems; // number of data items

    // -----------------------------------------------------------
    public OrdArray(int max) // constructor
    {
        a = new long[max]; // create array
        nElems = 0;
    }

    // -----------------------------------------------------------
    public int size() {
        return nElems;
    }

    // -----------------------------------------------------------
    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;

        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (a[curIn] == searchKey)
                return curIn; // found it
            else if (lowerBound > upperBound)
                return nElems; // can't find it
            else // divide range
            {
                if (a[curIn] < searchKey)
                    lowerBound = curIn + 1; // it's in upper half
                else
                    upperBound = curIn - 1; // it's in lower half
            } // end else divide range
        } // end while
    } // end find()

    // -----------------------------------------------------------
    public void insert(long value) // put element into array
    {
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
    } // end insert()

    // -----------------------------------------------------------

    // ========================================================
    // 编程作业2.4 p50(69)
    public void insert1(long value) {
        if (nElems == 0) { // 没有元素，直接插入
            a[0] = value;
            nElems++;
            return;
        }

        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;
        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (lowerBound > upperBound) {
                break;
            }
            if (a[curIn] == value)
                break; // found it
            else if (a[curIn] < value) // divide range
            {
                if (curIn == nElems - 1) {
                    curIn = curIn + 1;
                    break;
                } else if (a[curIn + 1] >= value) {
                    curIn = curIn + 1;
                    break;
                } else {
                    lowerBound = curIn + 1; // 注意这里是+1
                }
            } else {
                if (curIn == 0) {
                    break;
                } else if (a[curIn - 1] <= value) {
                    break;
                } else {
                    upperBound = curIn - 1; // 注意这里是-1;
                }
            }
        }
        for (int k = nElems; k > curIn; k--)
            // move bigger ones up
            a[k] = a[k - 1];
        a[curIn] = value; // insert it
        nElems++; // increment size
    }

    public void insert2(long value) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;
        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (lowerBound > upperBound) {
                break;
            }
            if (a[curIn] == value)
                break; // found it
            else if (a[curIn] < value) // divide range
            {
                lowerBound = curIn + 1;
                if(lowerBound>=nElems) {
                    break;
                }
            } else {
                upperBound = curIn - 1;
                if(upperBound<0) {
                    curIn = upperBound;
                    break;
                }
            }
        }

        int index = curIn + 1;//需要将插入的位置+1
        for (int k = nElems; k > index; k--)
            // move bigger ones up
            a[k] = a[k - 1];
        a[index] = value; // insert it
        nElems++; // increment size
    }

    // ========================================================
    // p50(69) 编程作业2.4 delete()已经用到了二分查找
    // ========================================================
    public boolean delete(long value) {
        int j = find(value); // delete()已经用到了二分查找
        if (j == nElems) // can't find it
            return false;
        else // found it
        {
            for (int k = j; k < nElems; k++)
                // move bigger ones down
                a[k] = a[k + 1];
            nElems--; // decrement size
            return true;
        }
    } // end delete()

    // -----------------------------------------------------------
    public void display() // displays array contents
    {
        for (int j = 0; j < nElems; j++)
            // for each element,
            System.out.print(a[j] + " "); // display it
        System.out.println("");
    }

    // -----------------------------------------------------------
    // ==================================================
    // p50(69) 编程作业2.5
    public OrdArray merge(OrdArray orderArr) {
        // 假设数组空间总是足够
        OrdArray dist = new OrdArray(this.nElems + orderArr.nElems);
        int index = 0;
        for (int i = 0; i < orderArr.size(); i++) {
            dist.insert(orderArr.a[i]);
        }
        for (int i = 0; i < this.size(); i++) {
            dist.insert(this.a[i]);
        }
        return dist;
    }

    public OrdArray merge2(OrdArray orderArr) {
        // 假设数组空间总是足够
        int inElems = this.nElems;
        int jnElems = orderArr.nElems;
        int i = 0;
        int j = 0;
        OrdArray dist = new OrdArray(this.nElems + orderArr.nElems);
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
    // ==================================================

} // end class OrdArray
// //////////////////////////////////////////////////////////////
