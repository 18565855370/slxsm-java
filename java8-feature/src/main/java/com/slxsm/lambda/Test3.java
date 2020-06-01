package com.slxsm.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-05-28
 */
public class Test3 {

    public static void main(String[] args) {
        /*TheInterface theInterface = () -> {};
        System.out.println(theInterface.getClass().getInterfaces()[0]);

        TheInterface2 theInterface2 = () -> {};
        System.out.println(theInterface2.getClass().getInterfaces()[0]);

        new Thread(() -> {System.out.println("hello world, i work in jd");}).start();*/

        List<String> list = Arrays.asList("zhang","wen","jun","is","shuai","ge");
//        list.forEach(item -> System.out.println(item.toUpperCase()));
        List<String> list2 = new ArrayList<>();
        //方式1 -> 复杂方式
        /*list.forEach(item -> list2.add(item.toUpperCase()));
        list2.forEach(System.out::println);*/
        //方式2 - > 流方式, stream 是串行流， parallelStream是多线程流，效率要高些
//        list.stream()

    }

}

@FunctionalInterface
interface TheInterface {
    void myMethod();
}

interface TheInterface2 {
    void myMethod();
}