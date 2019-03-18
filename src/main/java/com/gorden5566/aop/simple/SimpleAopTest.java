package com.gorden5566.aop.simple;

import com.gorden5566.aop.simple.service.HelloService;
import com.gorden5566.aop.simple.service.impl.NoInterfaceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gorden5566
 * @date 2019-03-17
 */
public class SimpleAopTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/appcontext-simple.xml");

        testJdkProxy(context);

        testCglibProxy(context);
    }

    /**
     * 测试JDK动态代理
     *
     * @param context
     */
    public static void testJdkProxy(ApplicationContext context) {
        HelloService helloService = context.getBean(HelloService.class);
        System.out.println(helloService.getClass());
        helloService.sayHello("gorden5566");

        System.out.println();
    }

    /**
     * 测试Cglib动态代理
     *
     * @param context
     */
    public static void testCglibProxy(ApplicationContext context) {
        NoInterfaceImpl noInterface = context.getBean(NoInterfaceImpl.class);
        System.out.println(noInterface.getClass());
        noInterface.test("gorden5566");

        System.out.println();
    }
}
