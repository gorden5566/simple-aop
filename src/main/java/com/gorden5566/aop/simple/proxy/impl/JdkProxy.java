package com.gorden5566.aop.simple.proxy.impl;

import com.gorden5566.aop.simple.proxy.Interceptor;
import com.gorden5566.aop.simple.proxy.ProxyUtils;
import com.gorden5566.aop.simple.proxy.SimpleProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author gorden5566
 * @date 2019-03-18
 */
public class JdkProxy implements InvocationHandler, SimpleProxy {
    /**
     * 目标对象
     */
    private Object target;

    /**
     * 所有拦截器
     */
    private List<Interceptor> interceptors;

    public JdkProxy(Object target) {
        this.target = target;
    }

    @Override
    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public Object getProxy() {
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Interceptor> matchedInterceptors = ProxyUtils.getMatchedInterceptors(interceptors, method);

        // before逻辑
        ProxyUtils.invokeBefore(matchedInterceptors);

        Object result = method.invoke(target, args);

        // after逻辑
        ProxyUtils.invokeAfter(matchedInterceptors);

        return result;
    }


}
