package com.gorden5566.aop.simple.aspect;

import com.gorden5566.aop.simple.annotation.MyAfter;
import com.gorden5566.aop.simple.annotation.MyAspect;
import com.gorden5566.aop.simple.annotation.MyBefore;

/**
 * @author gorden5566
 * @date 2019-03-18
 */
@MyAspect
public class NoInterfaceAspect {
    @MyBefore("test(java.lang.String)")
    public void before() {
        System.out.println("I'm before method call! :)");
    }

    @MyAfter("test(java.lang.String)")
    public void after() {
        System.out.println("I'm after method call! :)");
    }
}
