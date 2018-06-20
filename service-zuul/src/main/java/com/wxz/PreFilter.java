package com.wxz;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangxianzheng on 2018/6/19 0019.
 * zuul 路由之前过滤器
 */
@Component
public class PreFilter extends ZuulFilter{
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     *      pre：路由之前
     *      routing：路由之时
     *      post： 路由之后
     *      error：发送错误调用
     *      filterOrder：过滤的顺序
     *      shouldFilter：这里可以写逻辑判断，是否要过滤，设计为true,永远过滤。
     *      run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     * @return
     */
    @Override
    public String filterType() {
        logger.info("PreFilter-filterType-start");
        return "pre";
    }

    /**
     * 过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        logger.info("PreFilter-filterOrder-start");
        return 0;// filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    }

    /**
     * 这里可以写逻辑判断，是否要过滤，设计为true,永远过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        logger.info("PreFilter-shouldFilter-start");
        return true;
    }

    /**
     * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        logger.info("PreFilter-run-start");
        /**
         * 判断是否有token，如果没有不允许通过
         */
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();
        String token=request.getParameter("token");
        if(StringUtils.isNotBlank(token)){
            //对请求进行路由
            requestContext.setSendZuulResponse(true);
            requestContext.setResponseStatusCode(200);
            requestContext.set("isSuccess",true);
        }else{
            //不对请求进行路由
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(400);
            requestContext.setResponseBody("token is empty");
            requestContext.set("isSuccess",false);
        }
        return null;
    }
}
