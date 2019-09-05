package com.slxsm.listener_event.listener;

import com.slxsm.listener_event.dto.UserBean;
import com.slxsm.listener_event.event.UserRegisterEvent;
import com.slxsm.listener_event.service.UserService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AsyncApplicationListener implements SmartApplicationListener {

    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == UserRegisterEvent.class;
    }

    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == UserService.class;
    }

    public int getOrder() {
        return 1;
    }

    @Async
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //转换事件类型
        UserRegisterEvent event = (UserRegisterEvent) applicationEvent;
        //获取用户注册对象信息
        UserBean user = event.getUser();
        System.out.println(String.format("用户：%s，注册成功，发送邮件通知",user.getUsername()));
    }
}
