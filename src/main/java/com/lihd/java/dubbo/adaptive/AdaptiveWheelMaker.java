package com.lihd.java.dubbo.adaptive;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-03-17 22:14
 **/
//@Adaptive
public class AdaptiveWheelMaker implements WheelMaker {

    @Override
    public Wheel makeWheel(URL url) {

        if (url == null) {
            throw new IllegalArgumentException("url == null");
        }

        // 1.从 URL 中获取 WheelMaker 名称
        String wheelMakerName = url.getParameter("wheel.maker");
        if (wheelMakerName == null) {
            throw new IllegalArgumentException("wheelMakerName == null");
        }

        // 2.通过 SPI 加载具体的 WheelMaker
        WheelMaker wheelMaker = ExtensionLoader
                .getExtensionLoader(WheelMaker.class).getExtension(wheelMakerName);

        // 3.调用目标方法
        return wheelMaker.makeWheel(url);
    }
}
