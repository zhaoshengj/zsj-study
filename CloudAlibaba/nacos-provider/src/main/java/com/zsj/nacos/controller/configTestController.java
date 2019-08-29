package com.zsj.nacos.controller;

import com.zsj.nacos.ES.restclient.ESUtils;
import com.zsj.nacos.entity.Item;
import com.zsj.nacos.entity.User;
import com.zsj.nacos.server.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
//@RefreshScope
public class configTestController {

    @Value("${server.port}")
    private String port;

    private static final Logger logger = LoggerFactory.getLogger(configTestController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void getConfig() {
        //System.out.println("provider1的端口是: "+port);
        //userService.insertService();

        User user = userService.selectUserByName("SnailClimb");
        user.setId(2);
        String id = String.valueOf(user.getId());
        try {

          /* if(ESUtils.existsIndex("zsj")){
               ESUtils.getIndex("zsj");
           }else {
               ESUtils.createIndex("zsj");
           }
           ESUtils.createDocument("zsj",id,JSON.toJSONString(user));
           ESUtils.getDocument("zsj",id);*/

           ESUtils.search("zsj","一个");
        } catch (Exception e) {
            logger.error(" 创建索引异常：", e);
        }
        System.out.println(user.toString());

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list() {
        //System.out.println("provider1的端口是: "+port);
        //userService.insertService();

        List<User> users = userService.selectAllUser();
        System.out.println(users.toString());

    }


    @RequestMapping(value = "/delete")
    public void delete() {

        userService.deleteService(1003);

    }
    @RequestMapping(value = "/insert")
    public void insert() {
        userService.insertService();

    }

    @RequestMapping(value = "/update")
    public void update() {
        userService.changemoney();

    }


    @RequestMapping(value = "/esInit")
    public void EsInit() {
        List<Item> users = new ArrayList<>();
        users.add(new Item("1","这是一个测试",1,new BigDecimal(100)));
        users.add(new Item("2","测试",2,new BigDecimal(200)));
        users.add(new Item("3","还是测试而已",3,new BigDecimal(300)));
        users.add(new Item("4","测一下",4,new BigDecimal(400)));
        users.add(new Item("5","测",5,new BigDecimal(500)));
    }

    @RequestMapping(value = "/esGet")
    public void EsGet() {

    }
}
