package com.slxsm.quick_sort;

import java.util.Arrays;

/**
 * @author slxsm
 * @date 2019-08-07 23:53
 * 快速排序算法-左右指针算法
 * 送给独自过节的自己
 */
public class Lr_Point_QuickSort {

    public static int[] sort(int[] arrays, int l, int r){
        if (arrays == null || arrays.length == 0){
            return arrays;
        }
        if (l >= r){
            return arrays;
        }
        int left = l;
        int right = r;
        int temp = arrays[left];//基准值
        while(left < right){
            while (left < right && arrays[right] >= temp){
                right--;
            }
            while(left < right && arrays[left] <= temp){
                left++;
            }
            if (left < right){
                swap(arrays,left,right);
            }
        }
        swap(arrays,l,left);//此处注意了，left是基准值
        System.out.println(Arrays.toString(arrays));
        sort(arrays,l,left - 1);
        sort(arrays,left + 1, r);
        return arrays;
    }

    private static void swap(int[] arrays, int left, int right){
        int temp = arrays[left];
        arrays[left] = arrays[right];
        arrays[right] = temp;
    }

    public static void main(String[] args) {
        //定义一个随机数组
        int[] arrays = {4,34,7,32,9,3,68,13,0};
        //解雇：[0, 3, 4, 7, 9, 13, 32, 34, 68]
        System.out.println(Arrays.toString(sort(arrays,0,arrays.length - 1)));
    }
}
