package com.zsj.nacos.controller;

import com.zsj.nacos.entity.User;
import com.zsj.nacos.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RefreshScope
public class configTestController {

    @Value("${server.port}")
    private String port;

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public void getConfig(){
        //System.out.println("provider1的端口是: "+port);
        //userService.insertService();

        User user = userService.selectUserByName("SnailClimb");
        System.out.println(user.toString());

    }
}
