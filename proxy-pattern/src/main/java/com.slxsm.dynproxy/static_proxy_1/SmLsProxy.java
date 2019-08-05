package com.slxsm.dynproxy.static_proxy_1;

public class SmLsProxy implements IlawSuit {

    private IlawSuit ilawSuit;

    public SmLsProxy(IlawSuit ilawSuit){
        this.ilawSuit = ilawSuit;
    }

    public void submit(String proof) {
        ilawSuit.submit(proof);
    }

    public void defend() {
        ilawSuit.defend();
    }
}
