package com.slxsm.redislock.service;

import com.slxsm.redislock.annotation.CacheLock;
import com.slxsm.redislock.annotation.LockedObject;

public interface SecKillInterface {
    /**
     * 暂时只支持在接口方法上注解
     */

    /**
     *  cacheLock注解可能会产生并发问题
     *  最简单的秒杀方法，参数是用户ID和商品ID。可能有多个线程争抢一个商品，所以商品ID加上LockedObject注解
     */

    @CacheLock(lockedPrefix = "TEST_PREFIX")
    public void secKill(String userId,@LockedObject Long commidityId);
}
