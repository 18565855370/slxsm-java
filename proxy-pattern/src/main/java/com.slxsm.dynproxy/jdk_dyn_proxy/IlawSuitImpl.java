package com.slxsm.dynproxy.jdk_dyn_proxy;

public class IlawSuitImpl implements IlawSuit {

    public void submit(String proof) {
        System.out.println(String.format("老板欠薪跑路，证据如下：%s",proof));
    }

    public void defend() {
        System.out.println(String.format("铁证如山，%s还牛翠花血汗钱","四毛"));
    }
}
