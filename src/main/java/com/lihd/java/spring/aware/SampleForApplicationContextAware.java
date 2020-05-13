package com.lihd.java.spring.aware;

import com.lihd.java.annotation.MyAnnotation;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: lihd-java
 * @description: sample for ApplicationContextAware
 * @author: li_hd
 * @create: 2020-05-13 15:31
 **/
@Component
public class SampleForApplicationContextAware implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        final Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(MyAnnotation.class);

        final String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

//        Stream.of(beanDefinitionNames).collect(Collectors.toList()).forEach(System.out::println);

        final MyAnnotation myAnnotation1 = applicationContext.findAnnotationOnBean("sampleForCycle1", MyAnnotation.class);

        System.out.println(getClass().getSimpleName() + " " + myAnnotation1.test());

        for (String next : beansWithAnnotation.keySet()) {
            System.out.println(String.format("key : %s  ; value : %s ", next, beansWithAnnotation.get(next)));
        }


    }


}
