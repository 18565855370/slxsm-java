package com.slxsm.lambda;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * ***************
 * 三毛菜鸟想进大厂*
 * ***************
 *
 * @author slxsm
 * @date 2020-06-04
 */
public class SupplierTest {


    public static void main(String[] args) {
//        Supplier<String> stringSupplier = () -> "hello world";
//        System.out.println(stringSupplier.get());

        Supplier<Student> supplier = () -> new Student();
        System.out.println(supplier.get().getAge());
        //构造函数引用
        Supplier<Student> supplier2 = Student::new;
        System.out.println(supplier2.get().getAge());
    }
}
