package com.slxsm.dynproxy.cglib_dyn_proxy;

/**
 * 被代理的业务类，被代理的对象没有实现任何接口
 */
public class Frank {

    public void submit(String proof){
        System.out.println(String.format("老板欠薪跑路，证据如下：%s",proof));
    }

    public void defend(){
        System.out.println(String.format("铁证如山，%s还Frank血汗钱","四毛"));
    }
}
