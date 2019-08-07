package com.slxsm.redislock.Lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * redisLock redis分布式锁实现
 * 加锁的使用方式：
 * lock();
 * try{
 *     executeMethod();
 * }catch(lockException e){
 *     thr
 * }finally{
 *     unlock();
 * }
 */
public class RedisLock {


    private static final long MILLI_NANO_TIME = 1000;

    private String key;

    private String lockId;

    private boolean lock = false;

    private static final Random RANDOM = new Random();

    public RedisLock(String lockPrefix, String objectValue){
        this.key = lockPrefix;
        this.lockId = objectValue;
    }

    private Jedis jedis = new Jedis("47.93.195.126",6379);


    /**
     * 加锁
     * @param timeout 超时时长
     * @param expire 过期时间
     * @return 加锁成功 if true
     */
    public boolean lock(long timeout, long expire){
        long nanoTime = System.nanoTime();
        timeout *= MILLI_NANO_TIME;//转换为毫秒 1毫秒 = 1000纳秒
        try{
            //在timeout范围内不断的轮询
            while (System.nanoTime() - nanoTime < timeout){
                //锁不存在的化，设置锁并设置过期时间==加锁
                if (jedis.setnx(this.key,lockId) == 1){
                    //设置锁的过期时间是为了在没有释放锁的情况下锁过期后消失，不会造成永久阻塞
                    jedis.pexpire(this.key,expire);
                    this.lock = true;
                    return this.lock;
                }
                System.out.println("锁等待");
                //短暂休眠，避免可能的活锁
                Thread.sleep(3,RANDOM.nextInt(30));
            }
        }catch (Exception e){
            throw new RuntimeException("locking error",e);
        }
        return false;
    }

    /**
     * 解锁
     */
    public void unlock(){
        try {
            if (this.lock){
                jedis.del(key);//直接删除
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
