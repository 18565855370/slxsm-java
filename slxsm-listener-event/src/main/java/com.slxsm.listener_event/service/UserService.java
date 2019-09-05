package com.slxsm.listener_event.service;

import com.slxsm.listener_event.dto.UserBean;
import com.slxsm.listener_event.event.UserRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    ApplicationContext context;

    public void register(UserBean user){
        //发布userRegisterEvent事件
        context.publishEvent(new UserRegisterEvent(this,user));
    }
}
