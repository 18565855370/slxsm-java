package com.slxsm.dynproxy.static_proxy_1;

import com.slxsm.dynproxy.static_proxy_0.StaticProxy;

/**
 * 实现接口的方式实现静态代理
 */
public class StaticProxyTest2 {

    public static void main(String[] args) {
        StaticFactory.getProxy().submit("工资流水在此");
        StaticFactory.getProxy().defend();
    }
}
