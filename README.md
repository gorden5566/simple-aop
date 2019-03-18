# simple-aop

一个简单的spring aop实现

## 原理

1. 通过实现 `BeanFactoryAware` 接口，获取 `beanFactory`，从中取到用于增强的 `Aspect bean`

2. 通过实现 `BeanPostProcessor` 接口，对符合条件的 bean 进行动态代理，加入对应的增强逻辑

# 使用方法

可使用 spring 的 xml 方式进行配置

## 开启 simple-aop

打开 spring 的 xml 配置文件，加入 `AopProxyBeanPostProcessor` 的 bean 配置

```xml
<bean class="com.gorden5566.aop.simple.AopProxyBeanPostProcessor"/>
```

## 配置 Aspect

### Aspect 定义

新建一个 Aspect 类，在类上加上 `MyAspect` 注解，该类用于定义 AOP 逻辑

新增方法，在方法上加上 `MyBefore` 或 `MyAfter` 注解，注解的值用于匹配要增强的方法（使用 String.contains 进行匹配）。方法内定义增强的逻辑

```java
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
```

### Aspect 配置

配置 Aspect 的 bean

```xml
<bean class="com.gorden5566.aop.simple.aspect.TestAspect"/>
```

## 测试

从 spring 容器中获取增强的 bean，调用对应方法测试

```java
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
```

输出结果：

```shell
class com.sun.proxy.$Proxy4
before method call
before111 method call
Hello: gorden5566
after method call

class com.gorden5566.aop.simple.service.impl.NoInterfaceImpl$$EnhancerByCGLIB$$2e56520b
I'm before method call! :)
Hello gorden5566, I have no interface
I'm after method call! :)

```
