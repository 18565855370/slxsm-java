package com.slxsm.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-06-20
 */
public class MethodReferenceDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","jello world");
        list.forEach(System.out::println);
    }
}
