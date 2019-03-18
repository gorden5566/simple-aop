package com.gorden5566.aop.simple.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author gorden5566
 * @date 2019-03-17
 */
public class Interceptor {
    /**
     * 目标对象
     */
    private Object target;

    /**
     * 待处理的方法
     */
    private Method method;

    /**
     * Advice配置的参数
     */
    private String regex;

    /**
     * 是否在前面加入增加逻辑
     */
    private boolean before;

    public Interceptor(Object target, Method method, String regex, boolean before) {
        this.target = target;
        this.method = method;
        this.regex = regex;
        this.before = before;
    }

    public Object invoke() {
        Object result = null;
        try {
            result = method.invoke(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean isBefore() {
        return before;
    }

    public String getRegex() {
        return regex;
    }
}
