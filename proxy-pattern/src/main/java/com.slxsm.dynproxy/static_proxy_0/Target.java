package com.slxsm.dynproxy.static_proxy_0;

/**
 * 被代理的目标类
 */
public class Target extends AbstractObject{

    protected void operation() {
        System.out.println("我爱money！！！");
    }
}
