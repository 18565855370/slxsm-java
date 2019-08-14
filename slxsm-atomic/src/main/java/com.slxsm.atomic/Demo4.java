package com.slxsm.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 针对AtomicReference（demo3）中出现的ABA问题修复，
 * AtomicStampReference内部维护了一个时间戳，每次compare的时候都会比较时间戳，这样就知道修改了几次
 *
 * 结果：
 * 当前余额：19，小于20元，充值成功20元，余额39元
 * 当前余额：39,大于10元，成功消费20元，余额：19元
 */
public class Demo4 {

    //账户余额
    static int accountMoney  = 19;
    //对账户余额做原子操作
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(accountMoney,0);

    /**
     * 模拟充值过程
     */
    public static void recharge(){
        for (int i = 0; i < 2; i++){
            int stamp = money.getStamp();
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    Integer m = money.getReference();
                    if (m == accountMoney){
                        if (money.compareAndSet(m,m + 20,stamp,stamp + 1)){
                            System.out.println("当前余额：" + m + "，小于20元，充值成功20元，余额" + money.getReference() + "元");
                        }
                    }
                    //休眠100ms
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

    public static void consum() throws InterruptedException {
        for (int i = 0; i < 5; i++){
            Integer m = money.getReference();
            int stamp = money.getStamp();
            if (m > 20){
                if (money.compareAndSet(m,m - 20,stamp,stamp+1)){
                    System.out.println("当前余额：" + m + ",大于10元，成功消费20元，余额：" + money.getReference() + "元");
                }
            }
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        recharge();
        consum();
    }
}
