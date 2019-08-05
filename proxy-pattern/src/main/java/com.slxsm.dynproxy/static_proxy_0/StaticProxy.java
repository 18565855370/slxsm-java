package com.slxsm.dynproxy.static_proxy_0;

/**
 * 静态代理
 */
public class StaticProxy extends AbstractObject{

    private Target target;

    public StaticProxy (Target target){
        this.target = target;
    }

    protected void operation() {
        if (target == null){
            target = new Target();
        }
        System.out.println("你爱什么？？？");
        target.operation();
        System.out.println("恭喜你！！！");
    }
}
