package com.slxsm.mybatis.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * sql拦截器，通过实现jdk动态代理实现拦截器
 */
@Slf4j
@Intercepts(@Signature(type= Executor.class,method ="query",args={MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class}))
public class SqlInterceptor implements Interceptor {

    //拦截方法
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("welcome Interceptor...");

        //获取原始的sql
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String oldSql = boundSql.getSql();
        log.info("old sql is {}",oldSql);

        Object result = invocation.proceed();
        return result;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    public void setProperties(Properties properties) {

    }
}
