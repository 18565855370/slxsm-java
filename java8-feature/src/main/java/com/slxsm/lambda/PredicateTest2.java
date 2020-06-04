package com.slxsm.lambda;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-06-02
 */
public class PredicateTest2 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);

        PredicateTest2 predicateTest2 = new PredicateTest2();
        predicateTest2.conditionFilter(list, item -> item % 2 == 0);

        System.out.println("=======================");
        predicateTest2.conditionFilter(list, item -> item % 2 != 0);
        System.out.println("=======================");
        predicateTest2.conditionFilter(list, item -> item > 5);
        System.out.println("=======================");
        predicateTest2.conditionFilter(list, item -> item < 3);
        System.out.println("=======================");
        predicateTest2.conditionFilter(list, item -> true);
        System.out.println("=======================");
        predicateTest2.conditionFilter(list, item -> false);

        System.out.println("---------------------------");
        predicateTest2.conditionFilter2(list, item -> item > 5,item -> item % 2 == 0);
        System.out.println("=======================");
        System.out.println(predicateTest2.isEquals("slxsm").test("test"));
    }
    
    public void conditionFilter(List<Integer> list, Predicate<Integer> predicate){
        for (Integer integer : list) {
            if (predicate.test(integer)){
                System.out.println(integer);
            }
        }
    }

    public void conditionFilter2(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> predicate2){
        for (Integer integer : list) {
            if (predicate.and(predicate2).negate().test(integer)){
                System.out.println(integer);
            }
        }
    }

    public Predicate<String> isEquals(Object o){
        return Predicate.isEqual(o);
    }
}
