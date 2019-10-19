package com.slxsm.direct_insert_sort;

import java.util.Arrays;

/**
 * 直接插入排序
 */
public class Demo1 {

    public static void main(String[] args) {
        int[] arrays = {4,2,6,1,6,3,5,8,5,7,6};
        sort(arrays);
    }

    public static void sort(int[] arrays){
        //先判断是否为空
        if (arrays == null || arrays.length == 0){
            return;
        }
        //排序
        int length = arrays.length;
        for (int i = 1; i < length; i++) {
            int temp = arrays[i];
            int j = i - 1;
            while(j >= 0 && arrays[j] > temp){
                arrays[j + 1] = arrays[j];
                j--;
            }
            arrays[j + 1] = temp;
        }

        System.out.println(Arrays.toString(arrays));
    }
}
