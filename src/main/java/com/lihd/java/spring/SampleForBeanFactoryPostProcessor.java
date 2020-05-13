package com.lihd.java.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-03-18 19:51
 **/
@Service
public class SampleForBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        final BeanDefinition sampleForCycle2 = configurableListableBeanFactory.getBeanDefinition("sampleForCycle2");

        sampleForCycle2.getPropertyValues().add("name","lihd");

        System.out.println(sampleForCycle2.getScope());

    }
}
