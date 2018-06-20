package com.wxz;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangxianzheng on 2018/6/19 0019.
 * zuul 路由之后过滤器
 */
@Component
public class PostFilter extends ZuulFilter{
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
        logger.info("PostFilter-filterType-start");
        return "post";
    }

    /**
     * 过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        logger.info("PostFilter-filterOrder-start");
        return 1;
    }

    /**
     * 这里可以写逻辑判断，是否要过滤，设计为true,永远过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        logger.info("PostFilter-shouldFilter-start");
        // 判断上一个过滤器结果为true，否则就不走下面过滤器，直接跳过后面的所有过滤器并返回 上一个过滤器不通过的结果。
        RequestContext requestContext=RequestContext.getCurrentContext();
        boolean isSuccess= (boolean) requestContext.get("isSuccess");
        return isSuccess;
    }

    /**
     * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        logger.info("PostFilter-run-start");
        /**
         * 判断用密码是否正确，这里可以通过查数据库进行匹配
         */
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();
        String password=request.getParameter("password");
        if("wxz".equals(password)){
            requestContext.setResponseStatusCode(200);
            requestContext.setSendZuulResponse(true);
            requestContext.set("isSuccess",true);
        }else{
            requestContext.setResponseStatusCode(400);
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("the password cannot be empty ");
            requestContext.set("isSuccess",false);
        }
        return null;
    }
}
