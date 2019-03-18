package com.gorden5566.aop.simple.proxy;

import com.gorden5566.aop.simple.proxy.impl.CglibProxy;
import com.gorden5566.aop.simple.proxy.impl.JdkProxy;

/**
 * @author gorden5566
 * @date 2019-03-18
 */
public class SimpleProxyFactory {

    /**
     * 若实现了接口则使用JDK动态代理，否则使用Cglib动态代理
     *
     * @param bean
     * @return
     */
    public static SimpleProxy getSimpleProxy(Object bean) {
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        if (interfaces.length == 0) {
            return new CglibProxy(bean);
        }
        return new JdkProxy(bean);
    }
}
