package com.lihd.java.spring.cycle;

import com.lihd.java.annotation.MyAnnotation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-12 20:32
 **/
@Service
@MyAnnotation(test = "lihd")
public class SampleForCycle1 {

    @Resource
    private SampleForCycle2 sampleForCycle2;


//    @Autowired
//    public SampleForCycle1(SampleForCycle2 sampleForCycle2) {
//        this.sampleForCycle2 = sampleForCycle2;
//    }


    public void test() {
        System.out.println(sampleForCycle2.getName());
    }

}
