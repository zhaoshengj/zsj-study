package com.zsj.nacosconsumer.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("nacos-provider")
public interface NacosClient {

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    String get();
}
