package com.gorden5566.aop.simple.aspect;

import com.gorden5566.aop.simple.annotation.MyAfter;
import com.gorden5566.aop.simple.annotation.MyAspect;
import com.gorden5566.aop.simple.annotation.MyBefore;

/**
 * @author gorden5566
 * @date 2019-03-17
 */
@MyAspect
public class TestAspect {

    @MyBefore("sayHello(java.lang.String)")
    public void before() {
        System.out.println("before method call");
    }

    @MyBefore("sayHello(java.lang.String)")
    public void before1() {
        System.out.println("before111 method call");
    }

    @MyAfter("sayHello(java.lang.String)")
    public void after() {
        System.out.println("after method call");
    }


}
