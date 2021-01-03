package com.slxsm.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-07-01
 */
public class StreamTest1 {

    //创建流方式1
    Stream stream1 = Stream.of("hello", "world", "hello world");
    //创建流方式2
    String[] myArray = new String[]{"hello", "world", "hello world"};
    Stream stream2 = Stream.of(myArray);
    //创建流方式3
    Stream stream3 = Arrays.stream(myArray);
    //创建流方式4
    List<String> list = Arrays.asList(myArray);
    Stream stream4 = list.stream();
}
