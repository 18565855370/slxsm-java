package com.slxsm.dynproxy.cglib_dyn_proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CglibDynProxy implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.getName().equals("submit")){
            System.out.println("案件提交成功，证据如下：" + Arrays.asList(objects));
        }
        Object result = methodProxy.invokeSuper(o,objects);
        return result;
    }
}
