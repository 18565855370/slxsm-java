package com.slxsm.defaultmethod;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-06-29
 */
public interface MyInterface2 {

    default void myMethod(){
        System.out.println("MyInterface2");
    }
}
