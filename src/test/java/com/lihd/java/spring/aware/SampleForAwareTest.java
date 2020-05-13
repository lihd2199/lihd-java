package com.lihd.java.spring.aware;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: lihd-java
 * @description: test SampleForApplicationContextAware
 * @author: li_hd
 * @create: 2020-05-13 15:34
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class SampleForAwareTest {

    @Test
    public void testSampleForApplicationContextAware(){


    }


}
