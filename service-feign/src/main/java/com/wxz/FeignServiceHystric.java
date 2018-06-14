package com.wxz;

import org.springframework.stereotype.Component;

/**
 * Created by wangxianzheng on 2018/6/14 0014.
 * 熔断方法接口
 * 实现要进行熔断的接口
 */
@Component
public class FeignServiceHystric implements FeignService {

    /**
     * FeignService 接口对应的熔断方法
     * @param name
     * @return
     */
    @Override
    public String dofeign(String name) {
        return "hi "+name+",sorry error";
    }
}
