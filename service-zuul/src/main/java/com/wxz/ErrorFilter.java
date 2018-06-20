package com.wxz;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wangxianzheng on 2018/6/19 0019.
 * zuul 路由转发错误过滤器
 */
@Component
public class ErrorFilter extends ZuulFilter{
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     *      pre：路由之前
     *      routing：路由之时
     *      post： 路由之后
     *      error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        logger.info("ErrorFilter-filterType-start");
        return "error";
    }

    /**
     * 过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        logger.info("ErrorFilter-filterOrder-start");
        return 3;
    }

    /**
     * 这里可以写逻辑判断，是否要过滤，设计为true,永远过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        logger.info("ErrorFilter-shouldFilter-start");
        return true;
    }

    /**
     * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        logger.info("ErrorFilter-run-start");
        return null;
    }
}
