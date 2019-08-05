package com.slxsm.dynproxy.static_proxy_1;

public class StaticFactory {

    public static IlawSuit getProxy(){
        return new SmLsProxy(new SmShuai());
    }
}
