package com.lihd.java.snowflake;

import org.junit.Test;

/**
 * @author: li_hd
 * @date: 2020-06-23 17:06
 **/
public class SnowflakeTest {

    @Test
    public void nextId_test(){

        final Snowflake snowflake = new Snowflake();
        final long id = snowflake.nextId();
        System.out.println(id);
    }

}
