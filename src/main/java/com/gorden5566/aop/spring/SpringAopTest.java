package com.gorden5566.aop.spring;

import com.gorden5566.aop.spring.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gorden5566
 * @date 2019-03-17
 */
public class SpringAopTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/appcontext.xml");
        HelloService helloService = context.getBean(HelloService.class);
        helloService.sayHello("gorden5566");
    }
}
