package com.gorden5566.aop.simple.service.impl;

import com.gorden5566.aop.simple.service.HelloService;

/**
 * @author gorden5566
 * @date 2019-03-17
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello: " + name);
    }
}
