package com.lihd.java.spring;

import com.lihd.java.spring.cycle.SampleForCycle1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-12 20:29
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class SampleForBeanFactoryPostProcessorTest {

    @Resource
    private SampleForCycle1 sampleForCycle1;

    @Test
    public void test(){

        sampleForCycle1.test();

    }


}
