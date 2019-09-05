package com.slxsm.listener_event.listener;

import com.slxsm.listener_event.dto.UserBean;
import com.slxsm.listener_event.event.UserRegisterEvent;
import com.slxsm.listener_event.service.UserService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SmartUserRegisterListener implements SmartApplicationListener {

    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == UserRegisterEvent.class;
    }

    public boolean supportsSourceType(Class<?> sourceType) {
        return  sourceType == UserService.class;
    }

    /**
     * 返回值越小，优先级越高
     * @return
     */
    public int getOrder() {
        return 0;
    }

    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //转换事件类型
        UserRegisterEvent event = (UserRegisterEvent) applicationEvent;
        //获取注册用户对象信息
        UserBean user = event.getUser();
        // 输出注册信息
        System.out.println(String.format("SmartApplicationListener注册信息，用户名: " + user.getUsername() + ",密码：" + user.getPassword()));
    }
}
