package com.lihd.java.basicdatatype;

import org.junit.Assert;

/**
 * @program: lihd-java
 * @description:  sample for {@link Integer}
 * @author: li_hd
 * @create: 2020-05-09 09:51
 **/
public class SampleForInteger {

    /**
     * 测试缓存
     */
    public void sampleForIntegerCache() {

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


    public void sampleForHexString(Integer value) {

        String hex = Integer.toHexString(value);
        System.out.println(hex);

        int mag = Integer.SIZE - Integer.numberOfLeadingZeros(value);
        int chars = Math.max(((mag + (4 - 1)) / 4), 1);
        char[] buf = new char[chars];
        formatUnsignedInt(value, 4, buf, 0, chars);
        final String string = new String(buf);

        System.out.println(string);

    }

    static void formatUnsignedInt(int val, int shift, char[] buf, int offset, int len) {
        int charPos = len;
        int radix = 1 << shift;
        int mask = radix - 1;
        do {
            buf[offset + --charPos] = digits[val & mask];
            val >>>= shift;
        } while (val != 0 && charPos > 0);

    }

    final static char[] digits = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };


}
