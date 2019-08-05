package com.slxsm.dynproxy.jdk_dyn_proxy;

public class JdkProxyTest {

    public static void main(String[] args) {
        IlawSuit ilawSuit = (IlawSuit) ProxyFactory.getDynProxy(new IlawSuitImpl());
        ilawSuit.submit("工资流水线在此");
        ilawSuit.defend();
    }
}
