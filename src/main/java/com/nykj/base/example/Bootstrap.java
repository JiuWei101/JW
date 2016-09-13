package com.nykj.base.example;

import com.nykj.base.example.util.SpringBeanUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by cq on 4/17/16.
 */
public class Bootstrap implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        boot();
    }

    public void contextDestroyed(ServletContextEvent event) {
    }

    /**
     * 项目拉起时的初始化活动,可以在此处初始化自定义的任务
     */
    private void boot() {
        final ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/spring-all.xml");

        // 准备全局getBean的容器
        SpringBeanUtil.init(context);

    }
}
