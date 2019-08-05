package com.slxsm.dynproxy.cglib_dyn_proxy;

import net.sf.cglib.proxy.Enhancer;

public class ProxyFactory {

    public static Object getCglibDynProxy(Object target){
        Enhancer enhancer = new Enhancer();
         enhancer.setSuperclass(target.getClass());
         enhancer.setCallback(new CglibDynProxy());
         Object targetProxy = enhancer.create();
         return targetProxy;
    }
}
