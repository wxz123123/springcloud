package com.wxz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangxianzheng on 2018/6/12 0012.
 */
@RestController
public class HelloController {
    @Value("${name}")
    private String name;
    @RequestMapping("/getConfig")
    public String getConfig(){
        return "我是"+name;
    }
}
