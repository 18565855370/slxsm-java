package com.slxsm.bubble_sort;

import java.util.Arrays;

/**
 * 冒泡排序：两两比较，一轮下来最后的一位是最大的，然后再比较，直至最后排序完成
 */
public class BubbleSort_1 {

    public static void main(String[] args) {
        int[] arrays = {4,2,6,1,8,2,8,0,19,2,41,9};
        System.out.println(Arrays.toString(sort(arrays)));
    }

    private static int[] sort(int[] arrays){
        if (arrays == null && arrays.length == 0){
            return null;
        }

        for (int i = 0; i < arrays.length - 1; i++) {
            for (int j = 0; j < arrays.length - 1 - i; j++) {
                if (arrays[j] > arrays[j + 1]){
                    swap(arrays,j,j+1);
                }
            }
        }
        return arrays;
    }

    private static void swap(int[] arrays,int i,int j){
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }
}
