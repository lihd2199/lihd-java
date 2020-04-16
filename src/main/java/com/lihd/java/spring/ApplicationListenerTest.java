package com.lihd.java.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-03-18 19:49
 **/
@Component
public class ApplicationListenerTest implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        System.out.println(1);

    }
}
