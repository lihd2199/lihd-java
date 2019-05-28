package com.lihd.java.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author lihd
 * @desc
 */
public class ScheduledMessageProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        DefaultMQProducer producer = new DefaultMQProducer("lihd");
        producer.setNamesrvAddr("192.168.40.128:9876");
        producer.start();
        int totalMessagesToSend = 20;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("TopicTest", ("Hello scheduled message " + i).getBytes());
            message.setDelayTimeLevel(3);
            producer.send(message);
        }

        // Shutdown producer after use.
        producer.shutdown();



    }



}
