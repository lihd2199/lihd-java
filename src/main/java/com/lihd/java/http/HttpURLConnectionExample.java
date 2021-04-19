package com.lihd.java.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.CyclicBarrier;

/**
 * @program: lihd-java
 * @description: http client
 * @author: li_hd
 * @create: 2019-12-31 15:17
 **/
public class HttpURLConnectionExample {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(1);



        for (int i=0;i<1;i++){

            new Thread(new HttpURLConnection(cyclicBarrier,i)).start();

        }


    }


    static class HttpURLConnection implements Runnable {

        private CyclicBarrier cyclicBarrier;

        private int anInt;

        HttpURLConnection(CyclicBarrier cyclicBarrier, int i) {
            this.cyclicBarrier = cyclicBarrier;
            this.anInt = i;
        }

        @Override
        public void run() {

            try {

                JSONObject jsonObject = JSONObject.parseObject(QRY_SPEC_GROUP_PARAM);

                HttpClient client = HttpClientBuilder.create().build();

                final HttpPost httpPost = new HttpPost(QRY_SPEC_GROUP_URL);

                httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");


                // 解决中文乱码问题
                StringEntity stringEntity = new StringEntity(jsonObject.toString(), "UTF-8");
                stringEntity.setContentEncoding("UTF-8");

                httpPost.setEntity(stringEntity);

                ResponseHandler<String> responseHandler = response -> {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {

                        HttpEntity entity = response.getEntity();

                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        HttpEntity entity = response.getEntity();
                        System.out.println(EntityUtils.toString(entity));
                        throw new ClientProtocolException(
                                "Unexpected response status: " + status);
                    }
                };

                cyclicBarrier.await();

                String responseBody = client.execute(httpPost, responseHandler);
                System.out.println("----------------------------------------");
                System.out.println(responseBody);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }




    private static final String QRY_SPEC_GROUP_URL = "";


    private static final String QRY_SPEC_GROUP_PARAM = "";


}
