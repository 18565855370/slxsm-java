package com.slxsm.dynproxy.jdk_dyn_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static Object getDynProxy(Object target){
        InvocationHandler handler = new DynProxySuit(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),handler);
    }
}
