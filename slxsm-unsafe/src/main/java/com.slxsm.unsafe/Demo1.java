package com.slxsm.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe是一个单例，通过静态访问的时候回判断类加载器是不是引用类加载器（启动类加载器），不是的话不可以加载
 *
 * 可以通过反射的方式访问
 */
public class Demo1 {

    static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(unsafe);
    }
}
