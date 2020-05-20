package com.lihd.java.spring.cycle;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-12 20:33
 **/
@Service
public class SampleForCycle3 {

    @Resource
    private SampleForCycle1 sampleForCycle1;


//    Is there an unresolvable circular reference
//    @Autowired
//    public SampleForCycle3(SampleForCycle1 sampleForCycle1) {
//        this.sampleForCycle1 = sampleForCycle1;
//    }

}
