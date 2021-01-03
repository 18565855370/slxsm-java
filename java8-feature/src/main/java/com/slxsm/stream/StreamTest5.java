package com.slxsm.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-07-05
 */
public class StreamTest5 {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello","world","hello world", "test");
        List<String> list1 = stream.map(String::toUpperCase).collect(Collectors.toList());
        list1.forEach(System.out::println);
        System.out.println("-------------------");
        List<Integer> list2 = Arrays.asList(1,2,3,4,5);
        list2.stream().map(item -> item * item).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("--------------------");
        Stream<List<Integer>> stream1 = Stream.of(Arrays.asList(1),Arrays.asList(2,3),Arrays.asList(4,5,6));
        stream1.flatMap(theList -> theList.stream().map(item -> item * item)).forEach(System.out::println);
    }
}
