package com.lihd.java.spring;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-12 20:32
 **/
@Service
@Data
public class SampleForCycle2 {


    @Resource
    private SampleForCycle3 sampleForCycle3;

//    @Autowired
//    public SampleForCycle2(SampleForCycle3 sampleForCycle3) {
//        this.sampleForCycle3 = sampleForCycle3;
//    }


    private String name;

}
