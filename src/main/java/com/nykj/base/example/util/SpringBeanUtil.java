package com.nykj.base.example.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

/**
 * Created by Verson on 2016/2/29.
 * 需要用BeanUtil来全局执行getBean，以自主控制Bean的单例与多例场景
 */
public class SpringBeanUtil {
    private static BeanFactory factory;

    private SpringBeanUtil() {
    }

    public static void init(final BeanFactory factory) {
        SpringBeanUtil.factory = factory;
    }

    public static <E> E getBean(Class<E> requiredType) throws BeansException {
        return SpringBeanUtil.factory.getBean(requiredType);
    }

    public static Object getBean(String name) throws BeansException {
        return SpringBeanUtil.factory.getBean(name);
    }

    public static <E> E getBean(String name, Class<E> requiredType) throws BeansException {
        return SpringBeanUtil.factory.getBean(name, requiredType);
    }
}