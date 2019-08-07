package com.slxsm.redislock.interceptor;

import com.slxsm.redislock.Lock.RedisLock;
import com.slxsm.redislock.annotation.CacheLock;
import com.slxsm.redislock.annotation.LockedComplexObject;
import com.slxsm.redislock.annotation.LockedObject;
import com.slxsm.redislock.exception.CacheLockException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类的调用处理程序，需要实现InvocationHandler接口，
 */
public class CacheLockInterceptor implements InvocationHandler {
    //错误统计次数
    public static int ERROR_COUNT = 0;
    //被代理的对象
    private Object proxied;

    public CacheLockInterceptor(Object proxied){
        this.proxied = proxied;
    }

    //执行逻辑
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取方法使用的指定注解CacheLock
        CacheLock cacheLock = method.getAnnotation(CacheLock.class);
        //如果没有使用此注解，pass
        if (null == cacheLock){
            System.out.println("no cachelock annotation");
            return method.invoke(proxied,args);
        }
        //获取方法内的参数注解
        Annotation[][] annotations = method.getParameterAnnotations();
        //根据获取到的参数注解和参数列表获得加锁的参数
        Object lockedObject = getLockObject(annotations,args);
        String objectValue = lockedObject.toString();
        //新建一个锁
        RedisLock lock = new RedisLock(cacheLock.lockedPrefix(),objectValue);
        //加锁
        boolean resultLock = lock.lock(cacheLock.timeOut(),cacheLock.expireTime());
        if (!resultLock){
            ERROR_COUNT += 1;
            throw new CacheLockException("get lock fail");
        }
        try {
            //加锁成功，执行方法
            return method.invoke(proxied,args);
        }finally {
            //解锁
            lock.unlock();
        }
    }

    /**
     * 根据获取到的参数注解和参数列表获得加锁的参数
     * @param annotations 参数注解列表
     * @param args 参数数组
     * @return 返回加锁的参数
     */
    private Object getLockObject(Annotation[][] annotations, Object[] args) throws CacheLockException {
        if (null == args || args.length == 0){
            throw new CacheLockException("方法参数为空，没有被锁定的对象");
        }
        if (null == annotations || annotations.length == 0){
            throw new CacheLockException("没有被注解的参数");
        }
        //不支持多个参数加锁，只支持第一个注解为LockedObject或者LockComplexObject的参数
        //标记参数的位置指针
        int index = -1;
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                if (annotations[i][j] instanceof LockedComplexObject){
                    index = i;
                    try {
                        return args[i].getClass().getField(((LockedComplexObject)annotations[i][j]).field());
                    }catch (NoSuchFieldException | SecurityException e){
                        throw new CacheLockException("注解对象中没有该属性" + ((LockedComplexObject)annotations[i][j]).field());
                    }
                }
                if (annotations[i][j] instanceof LockedObject){
                    index = i;
                    break;
                }
            }
            //找到第一个后直接break，不支持多参数加锁
            if (index != -1){
                break;
            }
        }
        if (index == -1){
            throw new CacheLockException("请指定被锁定参数");
        }
        return args[index];
    }
}
