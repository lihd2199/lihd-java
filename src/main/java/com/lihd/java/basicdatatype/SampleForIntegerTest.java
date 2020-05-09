package com.lihd.java.basicdatatype;

import org.junit.Assert;
import org.junit.Test;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-09 09:51
 **/
public class SampleForIntegerTest {

    /**
     * 测试缓存
     */
    @Test
    public void testIntegerCache() {

        //自动装箱调用的Integer.valueOf方法 包含一个IntegerCache
        Integer i1 = 2;
        //new Integer() 会生成新的对象
        Integer i2 = new Integer(2);
        Integer i3 = 2;
        Integer i4 = Integer.valueOf(2);

        Assert.assertNotSame(i1, i2);
        Assert.assertSame(i3, i1);
        Assert.assertSame(i1, i4);

        Assert.assertNotSame(i2, i3);
        Assert.assertNotSame(i2, i4);

        Assert.assertSame(i3, i4);

    }



}
