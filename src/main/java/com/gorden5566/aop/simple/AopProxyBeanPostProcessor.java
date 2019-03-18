package com.gorden5566.aop.simple;

import com.gorden5566.aop.simple.annotation.MyAfter;
import com.gorden5566.aop.simple.annotation.MyBefore;
import com.gorden5566.aop.simple.helper.AspectHelper;
import com.gorden5566.aop.simple.proxy.Interceptor;
import com.gorden5566.aop.simple.proxy.ProxyUtils;
import com.gorden5566.aop.simple.proxy.SimpleProxy;
import com.gorden5566.aop.simple.proxy.SimpleProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gorden5566
 * @date 2019-03-17
 */
public class AopProxyBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private AspectHelper aspectHelper;

    /**
     * 所有的interceptor定义
     */
    private volatile List<Interceptor> interceptors;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return wrapIfNecessary(bean, beanName);
    }

    public Object wrapIfNecessary(Object bean, String beanName) {
        Class<?> clazz = bean.getClass();
        if (ProxyUtils.isAspect(clazz)) {
            return null;
        }

        // 初始化
        initAspect();

        // 创建SimpleProxy
        SimpleProxy simpleProxy = SimpleProxyFactory.getSimpleProxy(bean);
        simpleProxy.setInterceptors(interceptors);

        return simpleProxy.getProxy();
    }

    /**
     * 初始化所有aspect
     */
    private void initAspect() {
        if (interceptors != null) {
            return;
        }
        List<Object> aspectBeans = aspectHelper.getAspectBeans();

        synchronized (this) {
            if (interceptors != null) {
                return;
            }

            List<Interceptor> tempInterceptors = new ArrayList<>();

            for (Object aspectBean : aspectBeans) {
                Class<?> clazz = aspectBean.getClass();
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    MyBefore myBefore = method.getAnnotation(MyBefore.class);
                    if (myBefore != null) {
                        tempInterceptors.add(new Interceptor(aspectBean, method, myBefore.value(), true));
                        continue;
                    }

                    MyAfter myAfter = method.getAnnotation(MyAfter.class);
                    if (myAfter != null) {
                        tempInterceptors.add(new Interceptor(aspectBean, method, myAfter.value(), false));
                        continue;
                    }
                }
            }

            interceptors = tempInterceptors;
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.aspectHelper = new AspectHelper((ConfigurableListableBeanFactory) beanFactory);
    }
}
