package com.slxsm.dynproxy.cglib_dyn_proxy;

public class CglibProxyTest {

    public static void main(String[] args) {
        Frank cProxy  = (Frank) ProxyFactory.getCglibDynProxy(new Frank());
        cProxy.submit("工资流水在此");
        cProxy.defend();
    }
}
