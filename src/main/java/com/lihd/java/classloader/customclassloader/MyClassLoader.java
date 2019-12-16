package com.lihd.java.classloader.customclassloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者：请叫我程序猿大人
 * 链接：https://zhuanlan.zhihu.com/p/54693308
 * 来源：知乎
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

public class MyClassLoader extends ClassLoader {

    //用于读取.Class文件的路径
    private String swapPath;

    //用于标记这些name的类是先由自身加载的
    private Set<String> useMyClassLoaderLoad;


    private MyClassLoader(String swapPath, Set<String> useMyClassLoaderLoad) {
        this.swapPath = swapPath;
        this.useMyClassLoaderLoad = useMyClassLoaderLoad;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if (c == null && useMyClassLoaderLoad.contains(name)) {
            //特殊的类让我自己加载
            c = findClass(name);
            if (c != null) {
                return c;
            }
        }
        return super.loadClass(name);
    }


    @Override
    protected Class<?> findClass(String name) {
        //根据文件系统路径加载class文件，并返回byte数组
        byte[] classBytes = getClassByte(name);
        //调用ClassLoader提供的方法，将二进制数组转换成Class类的实例
        return defineClass(name, classBytes, 0, classBytes.length);
    }


    private byte[] getClassByte(String name) {
        String className = name.substring(name.lastIndexOf('.') + 1) + ".class";
        try {
            FileInputStream fileInputStream = new FileInputStream(swapPath + className);
            byte[] buffer = new byte[1024];
            int length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((length = fileInputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }


    public static void main(String[] args) {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String swapPath = MyClassLoader.class.getResource("").getPath() ;
                String className = "com.lihd.java.classloader.Test";

                //每次都实例化一个ClassLoader，这里传入swap路径，和需要特殊加载的类名
                HashSet<String> hashSet = new HashSet<>();
                hashSet.add(className);
                MyClassLoader myClassLoader = new MyClassLoader(swapPath, hashSet);
                try {
                    //使用自定义的ClassLoader加载类，并调用printVersion方法。
                    Object o = myClassLoader.loadClass(className).newInstance();
                    o.getClass().getMethod("swapTest").invoke(o);
                } catch (Exception ignored) {

                }
            }
        }, 0, 2000);

    }

}

