package com.eussi.ch11_hash.util;

/**
 * @author wangxueming
 * @create 2020-03-03 20:22
 * @description
 */
public class StringDataItem {
    private String sData;               // data item (key)

    // --------------------------------------------------------------

    public StringDataItem(String str)          // constructor
    {
        sData = str;
    }

    // --------------------------------------------------------------
    public String getKey() {
        return sData;
    }
    // --------------------------------------------------------------
}