package com.lihd.java.date;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-01-10 14:55
 **/
public class TimestampTest {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    public static void main(String[] args) {


        String str = "2020-01-10 14:55";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        LocalDateTime localDateTime = LocalDateTime.parse(str,dateTimeFormatter);

        final Timestamp timestamp = Timestamp.valueOf(localDateTime);

        System.out.println(timestamp);


    }



}
