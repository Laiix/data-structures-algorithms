package com.eussi.ch06_recursion.util;

/**
 * @author wangxueming
 * @create 2020-01-09 18:29
 * @description
 */
public class Params     // parameters to save on stack
{
    public int n;
    public int returnAddress;

    public Params(int nn, int ra)
    {
        n=nn;
        returnAddress=ra;
    }
}  // end class Params
////////////////////////////////////////////////////////////////