package com.slxsm.stream;

import java.util.Arrays;
import java.util.List;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-07-01
 */
public class StreamTest3 {

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1,2,3,4,5);
        System.out.println(intList.stream().mapToInt(i -> i * 2).reduce(0, Integer::sum));
    }
}
