package com.gorden5566.aop.simple.proxy.impl;

import com.gorden5566.aop.simple.proxy.Interceptor;
import com.gorden5566.aop.simple.proxy.ProxyUtils;
import com.gorden5566.aop.simple.proxy.SimpleProxy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author gorden5566
 * @date 2019-03-18
 */
public class CglibProxy implements MethodInterceptor, SimpleProxy {
    /**
     * 目标对象
     */
    private Object target;

    /**
     * 所有拦截器
     */
    private List<Interceptor> interceptors;

    public CglibProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object getProxy() {
        Class<?> clazz = target.getClass();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        List<Interceptor> matchedInterceptors = ProxyUtils.getMatchedInterceptors(interceptors, method);

        // before逻辑
        ProxyUtils.invokeBefore(matchedInterceptors);

        Object result = proxy.invokeSuper(obj, args);

        // after逻辑
        ProxyUtils.invokeAfter(matchedInterceptors);

        return result;
    }
}
