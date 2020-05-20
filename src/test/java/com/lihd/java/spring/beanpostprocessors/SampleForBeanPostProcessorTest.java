package com.lihd.java.spring.beanpostprocessors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-14 10:45
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class SampleForBeanPostProcessorTest {

    @Resource
    private BeanPostProcessorBean beanPostProcessorBean;

    @Test
    public void testSampleForBeanPostProcessor(){
        System.out.println(beanPostProcessorBean.getName());
    }


}
