package com.slxsm.listener_event.listener;

import com.slxsm.listener_event.dto.UserBean;
import com.slxsm.listener_event.event.UserRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotationRegisterListener {

    @EventListener
    public void register(UserRegisterEvent event){
        //获取注册用户对象
        UserBean user = event.getUser();

        // 输出注册用户信息
        System.out.println("@EventListener注册信息，用户名：" + user.getUsername() + ",密码：" + user.getPassword());
    }
}
