package com.lihd.java.snowflake;

/**
 * @author: li_hd
 * @date: 2020-06-23 16:54
 **/
public class Snowflake {

    private final long workerId;
    private final long datacenterId;
    private long sequence = 0L;
    private final long workerIdBits = 5L; //节点ID长度
    private final long datacenterIdBits = 5L; //数据中心ID长度
    private long lastTimestamp = -1L;

    public Snowflake() {
        this(0L, 0L);
    }

    public Snowflake(long workerId, long datacenterId) {
        //最大支持机器节点数0~31，一共32个
        long maxWorkerId = ~(-1L << workerIdBits);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("业务ID不能大于%d或小于0", maxWorkerId));
        }

        //最大支持数据中心节点数0~31，一共32个
        long maxDatacenterId = ~(-1L << datacenterIdBits);
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("数据中心ID不能大于%d或小于0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 调用该方法，获取序列ID
     */
    public synchronized long nextId() {
        long timestamp = currentTime(); //获取当前时间毫秒数
        //如果服务器时间有问题(时钟后退) 报错
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("时钟向后移动。拒绝在%d毫秒内生成id", lastTimestamp - timestamp));
        }

        //如果上次生成时间和当前时间相同,在同一毫秒内
        //序列号12位
        long sequenceBits = 12L;
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            //4095
            long sequenceMask = ~(-1L << sequenceBits);
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                timestamp = nextMillis(lastTimestamp); //自旋等待到下一毫秒
            }
        } else {
            sequence = 0L; //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
        }
        lastTimestamp = timestamp;
        // 最后按照规则拼出ID。
        // 000000000000000000000000000000000000000000 00000 00000 000000000000
        // time datacenterId workerId sequence
        //从该时间2018-01-01 00:00:00开始
        long twepoch = 1514736000000L;
        //机器节点左移12位
        //时间毫秒数左移22位
        long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
        //数据中心节点左移17位
        long datacenterIdShift = sequenceBits + workerIdBits;
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << sequenceBits) | sequence;
    }

    /**
     * 比较当前时间戳和下一个时间戳，如果下一个时间戳等于或小于当前时间戳，则循环获取下个时间戳
     * 该方法主要是避免同一时间获取同一时间戳
     */
    protected long nextMillis(long lastTimestamp) {
        long timestamp = currentTime();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTime();
        }
        return timestamp;
    }

    /**
     * 获取系统当前时间戳
     */
    protected long currentTime() {
        return System.currentTimeMillis();
    }


}
