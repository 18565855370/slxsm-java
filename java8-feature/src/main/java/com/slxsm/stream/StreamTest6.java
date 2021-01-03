package com.slxsm.stream;

import java.util.IntSummaryStatistics;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-07-05
 */
public class StreamTest6 {

    public static void main(String[] args) {
        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
//        System.out.println(stream.findFirst().get());
        System.out.println("--------------------------");
        stream.findFirst().ifPresent(System.out::println);
        System.out.println("----------------------------");
        Stream.iterate(1,item -> item + 2).limit(10).forEach(System.out::println);
        System.out.println("-------------------------------");
        Stream<Integer> stream1 = Stream.iterate(1, item -> item + 2).limit(6);
//        System.out.println(stream1.filter(item -> item > 200).mapToInt(item -> item * 2).skip(2).limit(2).sum());
//        System.out.println(stream1.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).max().orElse(1));
        stream1.filter(item -> item > 200).mapToInt(item -> item * 2).skip(2).limit(2).max().ifPresent(System.out::println);
        IntSummaryStatistics intSummaryStatistics =
                stream1.filter(item -> item > 2).mapToInt(item -> item * 2).skip(2).limit(2).summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getCount());
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getSum());
    }
}
