package com.gorden5566.aop.simple.service.impl;

import com.gorden5566.aop.simple.service.TestService;

/**
 * @author gorden5566
 * @date 2019-03-18
 */
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        System.out.println("this is a test");
    }
}
