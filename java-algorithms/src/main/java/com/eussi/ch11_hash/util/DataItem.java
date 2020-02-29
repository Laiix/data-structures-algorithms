package com.eussi.ch11_hash.util;

/**
 * @author wangxueming
 * @create 2020-02-29 2:05
 * @description
 */
public class DataItem {                                // (could have more data)
    private int iData;               // data item (key)

    //--------------------------------------------------------------
    public DataItem(int ii)          // constructor
    {
        iData = ii;
    }

    //--------------------------------------------------------------
    public int getKey() {
        return iData;
    }
//--------------------------------------------------------------
}  // end class DataItem