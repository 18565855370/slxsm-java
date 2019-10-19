package com.slxsm.merge_sort;

import java.util.Arrays;

/**
 * 归并排序，比较稳定的排序方式，将数组分为一段一段的，最终将子序列排序，排序完成后合并为一个有序整序列
 */
public class Demo1 {

    public static void main(String[] args) {
        int[] a = {3,1,6,3,2,5,7,6,10,9};
        System.out.println(Arrays.toString(sort(a)));
    }

    private static int[] sort(int[] a){
        if (a.length <= 1){
            return a;
        }
        int num = a.length >> 1;
        int[] a1 = Arrays.copyOfRange(a,0,num);
        int[] a2 = Arrays.copyOfRange(a,num,a.length);
        return mergeToArrays(sort(a1),sort(a2));
    }

    private static int[] mergeToArrays(int[] a, int[] b){
        int i = 0, j = 0, k = 0;
        int[] result = new int[a.length + b.length];//申请额外空间保存归并之后的数据
        while (i < a.length && j < b.length){
            if (a[i] < b[j]){
                result[k++] = a[i++];
            }else {
                result[k++] = b[j++];
            }
        }
        while (i < a.length){
            result[k++] = a[i++];
        }
        while (j < b.length){
            result[k++] = b[j++];
        }
        return result;

    }
}
