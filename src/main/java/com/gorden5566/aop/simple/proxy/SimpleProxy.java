package com.gorden5566.aop.simple.proxy;

import java.util.List;

/**
 * @author gorden5566
 * @date 2019-03-18
 */
public interface SimpleProxy {

    /**
     * 获取代理对象
     *
     * @return
     */
    Object getProxy();

    /**
     * 设置代理的拦截器
     *
     * @param interceptors
     */
    void setInterceptors(List<Interceptor> interceptors);
}
