package com.slxsm.listener_event.listener;

import com.slxsm.listener_event.dto.UserBean;
import com.slxsm.listener_event.event.UserRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegisterListener implements ApplicationListener<UserRegisterEvent> {


    public void onApplicationEvent(UserRegisterEvent event) {
        //获取用户注册对象
        UserBean user = event.getUser();

        // 邮件注册
        System.out.println(String.format("ApplicationListener给%s发邮件成功！！！",user.getPassword()));
    }
}
