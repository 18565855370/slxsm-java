package com.slxsm.dynproxy.static_proxy_1;

public class SmShuai implements IlawSuit {

    public void submit(String proof) {
        System.out.println(String.format("老板欠薪跑路，证据如下：%s",proof));
    }

    public void defend() {
        System.out.println(String.format("铁证如山，%s还钱","四毛"));
    }
}
