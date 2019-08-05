package com.slxsm.dynproxy.static_proxy_0;

/**
 * 采用继承抽象类实现静态代理
 */
public class StaticProxyTest1 {

    public static void main(String[] args) {
        AbstractObject obj = new StaticProxy(new Target());
        obj.operation();
    }



}
