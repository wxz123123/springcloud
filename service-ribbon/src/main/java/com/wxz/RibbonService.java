package com.wxz;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wangxianzheng on 2018/6/14 0014.
 * 消费者服务
 */
@Service
public class RibbonService {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @HystrixCommand 开启服务熔断功能
     * fallbackMethod 对应的熔断方法
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "isError")
    public String doRibbon(String name){
        /**
         * 通过restTemplate调用远程服务
         */
        return restTemplate.getForObject("http://client1/getPost?name="+name,String.class);
    }

    /**
     * 服务熔断方法
     * @param name
     * @return
     */
    public String isError(String name){
        return "hi "+name+",sorry error";
    }
}
