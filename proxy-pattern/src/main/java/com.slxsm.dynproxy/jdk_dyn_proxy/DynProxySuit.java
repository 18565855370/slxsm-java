package com.slxsm.dynproxy.jdk_dyn_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynProxySuit implements InvocationHandler {
    private Object target;

    public DynProxySuit(Object target){
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("案件进展" + method.getName());
        Object result = method.invoke(target,args);
        return result;
    }
}
