package com.gorden5566.aop.simple.helper;

import com.gorden5566.aop.simple.annotation.MyAspect;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author gorden5566
 * @date 2019-03-18
 */
public class AspectHelper {

    private ConfigurableListableBeanFactory beanFactory;

    private volatile List<Object> cachedAspects;

    public AspectHelper(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 获取所有的aspect bean
     *
     * @return
     */
    public List<Object> getAspectBeans() {
        if (cachedAspects != null) {
            return cachedAspects;
        }

        synchronized (this) {
            if (cachedAspects != null) {
                return cachedAspects;
            }

            List<Object> aspects = new ArrayList<>();
            Map<String, Object> beansOfType = beanFactory.getBeansOfType(Object.class);

            for (Map.Entry<String, Object> entry : beansOfType.entrySet()) {
                Object bean = entry.getValue();
                MyAspect myAspect = bean.getClass().getAnnotation(MyAspect.class);
                if (myAspect != null) {
                    aspects.add(bean);
                }
            }

            cachedAspects = aspects;
        }

        return cachedAspects;
    }
}
