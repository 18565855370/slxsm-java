package com.slxsm.direct_insert_sort;

import java.util.Arrays;

/**
 * @author slxsm
 * @date 2019-08-11 15:19
 * 直接插入排序之位移排序算法
 */
public class OffsetDirectInsertSort {

    public static void main(String[] args) {
        //待排序数组
        int[] arrays = {4,2,7,11,9,2,1,5,6,88,0};
        //大于结果：[0, 1, 2, 2, 4, 5, 6, 7, 9, 11, 88]
        System.out.println(Arrays.toString(sort(arrays)));
    }

    public static int[] sort(int[] arrays){
        if (arrays == null || arrays.length == 0){
            return arrays;
        }
        //循环排序，i = 1的意思是第一位默认是已排序的
        for (int i = 1; i < arrays.length; i++) {
            //这里的意思是新元素是i，那么新元素要比较的已排序数组时0到i-1
            int j = i - 1;
            //将要与前面的已排序的数组比较的元素
            int temp = arrays[i];
            //由于从后扫描到前面，素有以j要大于等于0，否则要数组越界了，如果前一位大于新元素，则将已排序袁旭向后移一位
            //这里我跟着学习的教程有问题，他写的时arrays[j]>arrays[i]因为大于的化会向后移一位，这样的化i的值会被覆盖，故我这里用temp代替arrays[i]
            while (j >= 0 && arrays[j] > temp){
                //位置交换
                arrays[j+1] = arrays[j];
                j--;
            }
            //将最终的小值放到该位置
            arrays[j+1] = temp;
        }
        return arrays;
    }
}
