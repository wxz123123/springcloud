package com.wxz;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wangxianzheng on 2018/6/14 0014.
 * feign 消费者服务
 * @FeignClient 的值client1是要调用的服务的名称（在eureka-server注册中心注册的服务名称，也就是对应服务配置文件中spring.application.name）
 */
@FeignClient(value = "client1",fallback = FeignServiceHystric.class)
interface FeignService {
    @RequestMapping("getPost")//getPost对应服务提供者的接口(也就是上面client1里面的接口地址)
    String dofeign(@RequestParam(value = "name") String name);
}
