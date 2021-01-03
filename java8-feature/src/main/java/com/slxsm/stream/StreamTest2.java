package com.slxsm.stream;

import java.awt.*;
import java.util.stream.IntStream;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-07-01
 */
public class StreamTest2 {

    public static void main(String[] args) {
        IntStream.of(new int[]{5,6,7}).forEach(System.out::println);
        System.out.println("==================");
        IntStream.range(3,8).forEach(System.out::println);
        System.out.println("==================");
        IntStream.rangeClosed(3,8).forEach(System.out::println);
    }
}
