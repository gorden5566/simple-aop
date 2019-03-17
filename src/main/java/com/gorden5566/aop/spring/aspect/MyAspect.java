package com.gorden5566.aop.spring.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author gorden5566
 * @date 2019-03-17
 */
@Aspect
public class MyAspect {

    @Pointcut("execution(public * *(..))")
    private void publicMethod() {
    }

    @Pointcut("within(com.gorden5566.aop.spring.service..*)")
    private void myPackage() {
    }

    @Before("publicMethod() && myPackage()")
    public void before() {
        System.out.println("before method call");
    }

    @After("publicMethod() && myPackage()")
    public void after() {
        System.out.println("after method call");
    }
}
