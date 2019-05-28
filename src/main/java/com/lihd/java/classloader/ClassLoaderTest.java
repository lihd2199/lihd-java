package com.lihd.java.classloader;


import java.io.IOException;
import java.io.InputStream;

/**
 * @author lihd
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {


        ClassLoader classLoader = new ClassLoader() {

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1)+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is==null) {
                        return super.loadClass(name);
                    }
                    byte[] b =new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    return super.loadClass(name);
                }
            }
        };

        Object obj = classLoader.loadClass("PathClassLoader").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof  ClassLoaderTest);

    }



}
