package com.slxsm.redislock.exception;

public class CacheLockException extends RuntimeException {

    public CacheLockException(String message){
        super(message);
    }
}
