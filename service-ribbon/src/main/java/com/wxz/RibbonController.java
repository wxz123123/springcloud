package com.wxz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangxianzheng on 2018/6/14 0014.
 */
@RestController
public class RibbonController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RibbonService ribbonService;
    @RequestMapping("/doRibbon")
    public String doRibbon(@RequestParam String name){
        logger.info("RibbonController-doRibbon-start");
        return ribbonService.doRibbon(name);
    }
}
