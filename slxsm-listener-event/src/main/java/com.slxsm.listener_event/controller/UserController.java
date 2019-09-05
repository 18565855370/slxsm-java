package com.slxsm.listener_event.controller;

import com.slxsm.listener_event.dto.UserBean;
import com.slxsm.listener_event.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/register")
    public String register(UserBean user){
        service.register(user);
        return "注册成功";
    }
}
