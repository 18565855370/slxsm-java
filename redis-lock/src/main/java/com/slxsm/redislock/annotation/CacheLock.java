package com.slxsm.redislock.annotation;

import java.lang.annotation.*;

/**
 * @author slxsm
 * cacheLock是方法级别的注解，用于注解会产生并发的方法上
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {

    //redis lock key prefix
    String lockedPrefix() default "";

    //轮询锁的时长，超过这个时间就代表超时，将锁强制删除（毫秒）
    long timeOut() default 2000;

    //key在redis中的存在时长
    long expireTime() default 1000;
}
