package com.zsj.nacosconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private NacosClient nacosClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String echoRestTemplate() {
        String result = restTemplate.getForObject("http://nacos-provider/get", String.class);
        System.out.println("Invoke : " + null + ", return : " + result);

        // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        String url = serviceInstance.getUri() + "/get";
        RestTemplate restTemplate = new RestTemplate();
        result = restTemplate.getForObject(url, String.class);
        return "Invoke : " + url + ", return : " + result;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String echoWebClient() {
        Mono<String> result = webClientBuilder.build().get().uri("http://nacos-provider/get").retrieve().bodyToMono(String.class);
        return result.toString();
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public String echoFeign() {
        String result = nacosClient.get();
        return  "Invoke : " + null + ", return : " + result;
    }

}
