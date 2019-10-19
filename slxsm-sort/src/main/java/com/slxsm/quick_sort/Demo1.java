package com.slxsm.quick_sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class Demo1 {

    public static void main(String[] args) {
        int[] arrays = {3,1,6,5,2,8,7,0,2,9};
        sort(arrays,0,arrays.length-1);
    }

    private static void sort(int[] arrays,int low, int hight){

        if (arrays == null || arrays.length == 0){
            return;
        }

        if (low >= hight){
            return;
        }

        int left = low;
        int right = hight;
        int temp = arrays[left];
        while (left < right){
            while (left < right && arrays[right] >= temp){
                right--;
            }
            arrays[left] = arrays[right];
            while (left < right && arrays[left] <= temp){
                left++;
            }
            arrays[right] = arrays[left];
        }
        arrays[left] = temp;
        System.out.println(Arrays.toString(arrays));
        sort(arrays,low,left - 1);
        sort(arrays,left + 1, hight);
    }
}
