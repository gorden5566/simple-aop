package com.gorden5566.aop.simple.proxy;

import com.gorden5566.aop.simple.annotation.MyAspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gorden5566
 * @date 2019-03-18
 */
public class ProxyUtils {
    /**
     * 判断是否有自定义的MyAspect注解
     *
     * @param clazz
     * @return
     */
    public static boolean isAspect(Class<?> clazz) {
        MyAspect annotation = clazz.getAnnotation(MyAspect.class);
        if (annotation != null) {
            return true;
        }
        return false;
    }

    /**
     * 获取匹配的拦截器
     *
     * @param interceptors
     * @param method
     * @return
     */
    public static List<Interceptor> getMatchedInterceptors(List<Interceptor> interceptors, Method method) {
        if (interceptors == null) {
            return null;
        }

        List<Interceptor> result = new ArrayList<>();
        for (Interceptor interceptor : interceptors) {
            if (method.toString().contains(interceptor.getRegex())) {
                result.add(interceptor);
            }
        }

        return result;
    }

    /**
     * 执行before拦截器
     *
     * @param interceptors
     */
    public static void invokeBefore(List<Interceptor> interceptors) {
        if (interceptors == null || interceptors.isEmpty()) {
            return;
        }

        for (Interceptor interceptor : interceptors) {
            if (interceptor.isBefore()) {
                interceptor.invoke();
            }
        }
    }

    /**
     * 执行after拦截器
     *
     * @param interceptors
     */
    public static void invokeAfter(List<Interceptor> interceptors) {
        if (interceptors == null || interceptors.isEmpty()) {
            return;
        }

        for (Interceptor interceptor : interceptors) {
            if (!interceptor.isBefore()) {
                interceptor.invoke();
            }
        }
    }
}
