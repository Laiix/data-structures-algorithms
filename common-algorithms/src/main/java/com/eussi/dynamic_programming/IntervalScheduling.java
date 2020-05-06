package com.eussi.dynamic_programming;

/**
 * @author wangxueming
 * @create 2020-05-07 0:08
 * @description
 */
public class IntervalScheduling {
    public static void intervalScheduling(int[][] intvs) {
        int[][] orderIntvs = sort(intvs);

        for(int i=0; i<orderIntvs.length; i++) {
            System.out.println(orderIntvs[i][0] + " " + orderIntvs[i][1]);
        }

        int count = 1;

        int end = orderIntvs[0][1];
        for(int i=1; i<orderIntvs.length; i++) {
            int start = orderIntvs[i][0];
            if(start>=end) {
                count ++;
                end = orderIntvs[i][1];
            }
        }

        System.out.println(count);
    }

    //插入排序
    private static int[][] sort(int[][] intvs) {
        for(int i=1; i<intvs.length; i++) {
            int[] temp = intvs[i];
            int j;
            for(j=i-1; j>=0; j--) {
                if(temp[1]<intvs[j][1]) {
                    intvs[j+1] = intvs[j];

                } else {
                    break;
                }

            }
            intvs[j+1] = temp;
        }
        return intvs;
    }
}
