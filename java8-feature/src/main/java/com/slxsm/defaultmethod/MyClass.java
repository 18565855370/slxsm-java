package com.slxsm.defaultmethod;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 * 实现类的优先级高于接口优先级
 * @author slxsm
 * @date 2020-06-29
 */
public class MyClass extends MyInterface1Impl implements MyInterface2{

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.myMethod();
    }
}
