package com.slxsm.lambda;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-06-04
 */
public class BinaryOperatorTest {

    public static void main(String[] args) {

       BinaryOperatorTest test = new BinaryOperatorTest();
        System.out.println(test.compute(1, 2, (a, b) -> a + b));
//        Comparator<Integer> comparator = (o1,o2) -> o1.compareTo(o2);
//        System.out.println(BinaryOperator.maxBy((comparator)).apply(8, 2));
        System.out.println(test.compute1("slxsm", "alxwm", (o1, o2) -> o1.compareTo(o2)));
    }

    public int compute(int a, int b, BinaryOperator<Integer> binaryOperator){
        return binaryOperator.apply(a,b);
    }

    public String compute1(String s1, String s2, Comparator<String> comparator){
        return BinaryOperator.minBy(comparator).apply(s1,s2);
    }
}
