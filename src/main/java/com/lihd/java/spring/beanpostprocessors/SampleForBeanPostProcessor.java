package com.lihd.java.spring.beanpostprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @program: lihd-java
 * @description: SampleForBeanPostProcessor
 * @author: li_hd
 * @create: 2020-05-14 09:58
 **/
@Component
public class SampleForBeanPostProcessor  implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("beanPostProcessorBean".equals(beanName)) {
            BeanPostProcessorBean beanPostProcessorBean = (BeanPostProcessorBean) bean;
            beanPostProcessorBean.setName("postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
