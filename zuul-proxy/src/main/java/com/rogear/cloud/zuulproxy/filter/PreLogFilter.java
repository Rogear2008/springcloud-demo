package com.rogear.cloud.zuulproxy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Rogear on 2020/3/9
 **/
@Component
public class PreLogFilter extends ZuulFilter {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 过滤器类型
     * pre：到达目的路由前执行
     * routing：到达目的路由时执行
     * post：到达目的路由后执行
     * error：错误时执行
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序，值越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否进行过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器执行内容
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        LOGGER.info("Remote host:{},method:{},url:{}",host,method,requestURI);
        return null;
    }
}
