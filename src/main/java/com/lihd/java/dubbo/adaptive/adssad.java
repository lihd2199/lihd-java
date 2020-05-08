//package com.lihd.java.dubbo.adaptive;
//
//import com.alibaba.dubbo.common.extension.ExtensionLoader;
//
//public class WheelMaker$Adaptive implements com.lihd.java.dubbo.adaptive.WheelMaker {
//    public com.lihd.java.dubbo.adaptive.Wheel makeWheel(com.alibaba.dubbo.common.URL arg0) {
//        if (arg0 == null) throw new IllegalArgumentException("url == null");
//        com.alibaba.dubbo.common.URL url = arg0;
//        String extName = url.getParameter("wheel.maker");
//        if (extName == null)
//            throw new IllegalStateException("Fail to get extension(com.lihd.java.dubbo.adaptive.WheelMaker) name from url(" + url.toString() + ") use keys([wheel.maker])");
//        com.lihd.java.dubbo.adaptive.WheelMaker extension = (com.lihd.java.dubbo.adaptive.WheelMaker) ExtensionLoader.getExtensionLoader(com.lihd.java.dubbo.adaptive.WheelMaker.class).getExtension(extName);
//        return extension.makeWheel(arg0);
//    }
//}
