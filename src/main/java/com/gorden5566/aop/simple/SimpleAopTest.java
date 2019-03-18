package com.gorden5566.aop.simple;

import com.gorden5566.aop.simple.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gorden5566
 * @date 2019-03-17
 */
public class SimpleAopTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/appcontext-simple.xml");
        HelloService helloService = context.getBean(HelloService.class);
        helloService.sayHello("gorden5566");
    }
}
