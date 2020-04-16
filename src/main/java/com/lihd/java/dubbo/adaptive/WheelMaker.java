package com.lihd.java.dubbo.adaptive;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-03-17 22:13
 **/
@SPI
public interface WheelMaker {

    @Adaptive
    Wheel makeWheel(URL url);

}
