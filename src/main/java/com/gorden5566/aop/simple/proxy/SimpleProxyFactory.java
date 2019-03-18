package com.gorden5566.aop.simple.proxy;

import com.gorden5566.aop.simple.proxy.impl.JdkProxy;

/**
 * @author gorden5566
 * @date 2019-03-18
 */
public class SimpleProxyFactory {
    public static SimpleProxy getSimpleProxy(Object bean) {
        return new JdkProxy(bean);
    }
}
