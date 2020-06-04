package com.slxsm.lambda;

import java.util.function.Predicate;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-06-02
 */
public class PredicateTest {

    public static void main(String[] args) {
        Predicate<String> predicate = p -> p.length() >= 5;
        System.out.println(predicate.test("slxsm"));
    }
}
