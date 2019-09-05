package com.slxsm.listener_event.event;

import com.slxsm.listener_event.dto.UserBean;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 创建一个事件，所有的listener监听器都是围绕着事件挂起的
 */
@Getter
public class UserRegisterEvent extends ApplicationEvent {

    //注册用户对象
    private UserBean user;

    /**
     * 重写ApplicationEvent构造函数
     * @param source 发生事件的对象
     * @param user 注册用户对象
     */
    public UserRegisterEvent(Object source,UserBean user) {
        super(source);
        this.user = user;
    }
}
