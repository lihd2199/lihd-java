package com.lihd.java.basicdatatype;

import com.lihd.java.basicdatatype.integer.SampleForInteger;
import org.junit.Test;

/**
 * @program: lihd-java
 * @description:  Test SampleForInteger
 * @author: li_hd
 * @create: 2020-05-13 17:17
 **/
public class SampleForIntegerTest {

    final SampleForInteger sampleForInteger = new SampleForInteger();
    @Test
    public void test_sampleForIntegerCache(){
        sampleForInteger.sampleForIntegerCache();
    }

    @Test
    public void test_sampleForHexString(){
        sampleForInteger.sampleForHexString(16);
    }


}
