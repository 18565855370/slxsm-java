package com.slxsm.redislock.annotation;

import java.lang.annotation.*;

/**
 * @author slxsm
 * lockedObject 是方法级别的注解，用于标注被加锁的对象，这里只用于基本类型，如商品的id
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObject {

}
