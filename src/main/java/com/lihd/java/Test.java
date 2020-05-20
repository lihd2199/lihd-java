package com.lihd.java;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-11 14:33
 **/
public class Test {

    private static final String QRY_SPEC_GROUP_URL = "http://apigateway.ctripcorp.com/restapi/soa2/12558/sendSettlement123";

    private static final Integer CODE = 200;

    public static void main(String[] args) throws IOException, InterruptedException {

        final File in = new File("D:\\value.txt");
        final FileReader fileReader = new FileReader(in);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        final HashSet<Long> set = new HashSet<>();

        while ((line = bufferedReader.readLine()) != null && !"".equals(line)) {
            set.add(Long.valueOf(line));
        }

        System.out.println("size:" + set.size());

        List<Long> longs = new ArrayList<>(set);

        final File file = new File("D:\\log.txt");
        final FileWriter fileWriter = new FileWriter(file);
        final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write("size:" + set.size());
        bufferedWriter.newLine();

        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 3000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),new ThreadPoolExecutor.CallerRunsPolicy());

        final CountDownLatch countDownLatch = new CountDownLatch(longs.size());

        longs.forEach(orderId -> threadPoolExecutor.execute(() -> {
            System.out.println(orderId);
            try {
                HttpClient client = HttpClientBuilder.create().build();
                JSONObject param = new JSONObject();
                param.put("orderId", orderId);
                param.put("customizeSendSettlement", true);
                final HttpPost httpPost = new HttpPost(QRY_SPEC_GROUP_URL);
                httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");

                // 解决中文乱码问题
                StringEntity stringEntity = new StringEntity(param.toJSONString(), "UTF-8");
                stringEntity.setContentEncoding("UTF-8");
                httpPost.setEntity(stringEntity);
                ResponseHandler<String> responseHandler = response -> {
                    int status = response.getStatusLine().getStatusCode();
                    HttpEntity entity = response.getEntity();
                    if (status == CODE) {
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        System.out.println(EntityUtils.toString(entity));
                        throw new ClientProtocolException(
                                "Unexpected response status: " + status);
                    }
                };
                String responseBody = client.execute(httpPost, responseHandler);
                synchronized (bufferedWriter) {
                    if (responseBody.contains(Boolean.TRUE.toString())) {
                        bufferedWriter.write(String.format("orderId: %d execute success ,response : %s", orderId, responseBody));
                    } else {
                        bufferedWriter.write(String.format("orderId: %d execute error ,response : %s", orderId, responseBody));
                    }
                }
            } catch (Exception e) {
                synchronized (bufferedWriter) {
                    try {
                        bufferedWriter.write(String.format("orderId: %d execute error ,error : %s", orderId, e.getMessage()));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } finally {
                countDownLatch.countDown();
                try {
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    //防止访问太频繁
                    Thread.sleep(100);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }));

        countDownLatch.await();
        threadPoolExecutor.shutdown();


    }


}
