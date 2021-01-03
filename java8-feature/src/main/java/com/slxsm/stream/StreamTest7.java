package com.slxsm.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-07-06
 */
public class StreamTest7 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","hello world");
//        list.stream().map(item -> item.substring(0,1).toUpperCase() + item.substring(1)).
//                forEach(System.out::println);
        list.stream().map(item -> {
           String result = item.substring(0,1).toUpperCase() + item.substring(1);
            System.out.println("test");
            return result;
        }).forEach(System.out::println);
    }
}
