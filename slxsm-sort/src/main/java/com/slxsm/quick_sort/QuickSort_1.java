package com.slxsm.quick_sort;

import java.util.Arrays;

/**
 * 快速排序
 * 第一步：选举一个基准值
 * 第二步：先右再左排序，一直排序到完
 * 第三步:然后再拆分再选举
 */
public class QuickSort_1 {

    public static void main(String[] args) {
        int[] arrays = {4,2,6,1,8,2,8,0,19,2,41,9};
        quickSort(arrays,0,arrays.length - 1);
    }

    private static void quickSort(int[] arrays, int low, int high){
        if (arrays == null || arrays.length <= 0){
            return;
        }
        if (low >= high){
            return;
        }
        int left = low;
        int right = high;
        int temp = arrays[left];
        while(left < right){
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
        System.out.println("Sorting: " + Arrays.toString(arrays));
        quickSort(arrays,low,left - 1);
        quickSort(arrays,left + 1,high);
    }
}
