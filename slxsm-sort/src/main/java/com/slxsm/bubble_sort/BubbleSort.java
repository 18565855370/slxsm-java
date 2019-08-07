package com.slxsm.bubble_sort;

import java.util.Arrays;

/**
 * @author slxsm
 * @date 2019-08-07 22:32
 * 冒泡排序
 * 送给情人节的自己😄
 */
public class BubbleSort {

    /**
     * 排序程序
     * @param arrays 待排序数组
     */
    public static int[] sort(int[] arrays){
        //检查数组是否为空，为空的无需比较，返回
        if (arrays == null || arrays.length == 0){
            return arrays;
        }
        int length = arrays.length;
        //共发起length - 1次流程排序
        for (int i = 0; i < length - 1; i++){
            //数组中每个元素都要两两比较，从第二轮也就是i = 1时，每一轮的最后一个元素不参与轮询比较
            for (int j = 0; j < length - 1 - i; j++){
                //开始比较
                if (arrays[j] > arrays[j+1]){
                    swap(arrays,j,j+1);
                }
            }
        }
        return arrays;
    }

    private static void swap(int[] arrays, int i, int j){
        //声明一个局部变量，将前一位交给临时变量
        int temp = arrays[i];
        //将后一位交给前一位
        arrays[i] = arrays[j];
        //将临时变量temp（前一位）交给后一位，完成交换
        arrays[j] = temp;
    }

    /**
     * main方法测试
     * @param args 参数 此处忽略
     */
    public static void main(String[] args) {
        //定义一个随机数组
        int[] arrays = {4,34,7,32,9,3,68,13,0};
        //调用排序方法sort并且打印,由于数组直接打印时内存地址，股使用Arrays.toString打印
        //排序结果：[0, 3, 4, 7, 9, 13, 32, 34, 68]
        System.out.println(Arrays.toString(sort(arrays)));
    }
}
