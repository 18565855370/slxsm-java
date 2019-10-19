package com.slxsm.xe_sort;

import java.util.Arrays;

/**
 * 希尔排序，是比较稳定的插入排序，还需再研究下
 */
public class XiErSort {

    public static int[] sort(int[] arrays){
        //首次步长
        int gap = arrays.length/2;
        for (;gap > 0; gap = gap/2){
            for (int j = 0; (j + gap) < arrays.length; j++) {
                for (int k = 0; (k + gap) < arrays.length; k+=gap) {
                    if (arrays[k] > arrays[k + gap]){
                        arrays[k] = arrays[k] + arrays[k + gap];
                        arrays[k + gap] = arrays[k] - arrays[k + gap];
                        arrays[k] = arrays[k] - arrays[k+gap];
                        System.out.println("    Sorting:    " + Arrays.toString(arrays));
                    }
                }
            }
        }
        return arrays;
    }

    public static void main(String[] args) {
        int[] arrays = {9,1,2,5,7,4,8,6,3,5};
        System.out.println("第一个哦" + Arrays.toString(arrays));
        System.out.println("    result  " + Arrays.toString(sort(arrays)));
    }
}
