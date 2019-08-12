package com.slxsm.direct_insert_sort;

import java.util.Arrays;

/**
 * @author slxsm
 * @date 2019-08-11 16:12
 * 直接插入排序之交换法
 */
public class CompareDirectInsertSort {

    public static void main(String[] args) {
        //待排序数组
        int[] arrays = {4,2,7,11,9,2,1,5,6,88,0};
        //大于结果：[0, 1, 2, 2, 4, 5, 6, 7, 9, 11, 88]
        System.out.println(Arrays.toString(sort(arrays)));
    }

    private static int[] sort(int[] arrays){
        if (arrays == null || arrays.length == 0){
            return arrays;
        }
        for (int i = 1; i < arrays.length; i++) {
            int j = i - 1;
            //原作者写的列子这里有问题，他是arrays[j] > arrays[i],从上个位移算法就知道这是有问题的，而且原例子中也没有j--，此处已修复
            while(j >=0 && arrays[j] > arrays[j + 1]){
                //这里采用运算的方式交换
                arrays[j + 1] = arrays[j] + arrays[j + 1];
                arrays[j] = arrays[j + 1] - arrays[j];
                arrays[j + 1] = arrays[j + 1] - arrays[j];
                j--;
            }
        }
        return arrays;
    }
}
