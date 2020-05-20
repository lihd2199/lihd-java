package com.lihd.java.spring.cycle;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-12 20:32
 **/
@Data
@Service
public class SampleForCycle1 {

    @Resource
    private SampleForCycle2 sampleForCycle2;

    private String name;

//    @Autowired
//    public SampleForCycle1(SampleForCycle2 sampleForCycle2) {
//        this.sampleForCycle2 = sampleForCycle2;
//    }


    public void test() {
        System.out.println(getName());
//        System.out.println(sampleForCycle2.getName());
    }

}
