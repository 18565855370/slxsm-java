package com.slxsm.quick_sort;

import java.util.Arrays;

/**
 * @author slxsm
 * @date 2019-08-07 23:27
 * 快速排序--挖坑排序算法
 */
public class WK_QuickSort {

    /**
     * 排序算法实现代码
     * @param arrays 待排序数组
     * @param l 左边界
     * @param r 右边界
     * @return 排序完成的数组
     */
    public static int[] sort(int[] arrays, int l, int r){
        //先检查是否为空，为空则返回null
        if (arrays == null || arrays.length == 0){
            return arrays;
        }
        if (l >= r){
            return arrays;
        }
        //选取最左边的值为基准值
        int left = l;
        int right = r;
        int temp = arrays[left];
        //左边的小于右边的，一旦相等就相当与两边的坑挖到了一起
        while (left < right){
            //右边遍历
            while (left < right && arrays[right] >= temp){
                //向前一位
                right--;
            }
            arrays[left] = arrays[right];//交换位置
            //左边遍历
            while (left < right && arrays[left] <= temp){
                //向后一位
                left++;
            }
            arrays[right] = arrays[left];
        }
        arrays[left] = temp;
        System.out.println(Arrays.toString(arrays));
        //递归排序left左边的
        sort(arrays, l, left - 1);
        //递归排序length右边的
        sort(arrays,left + 1, r);
        return arrays;
    }

    /**
     * 快速排序main
     * @param args 参数
     */
    public static void main(String[] args) {
        //定义一个随机数组
        int[] arrays = {4,34,7,32,9,3,68,13,0};
        //结果：[0, 3, 4, 7, 9, 13, 32, 34, 68]
        System.out.println(Arrays.toString(sort(arrays,0, arrays.length - 1)));
    }
}
