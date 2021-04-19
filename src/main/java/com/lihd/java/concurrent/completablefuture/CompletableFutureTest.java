package com.lihd.java.concurrent.completablefuture;

import com.lihd.java.concurrent.executors.ExecutorsTest;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-06-03 17:59
 **/
public class CompletableFutureTest {


    @Test
    public void testSupplyAsync() {

        CompletableFuture.supplyAsync(() -> {
            return 1;
        }, ExecutorsTest.getInstance()).whenComplete((v, e) -> {
            System.out.println(v);
        }).whenComplete((e,v) ->{
            System.out.println(e);
        });

    }


    @Test
    public void testRunAsync(){

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " : sleep 2000");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }, ExecutorsTest.getInstance()).whenComplete((v, e) -> {
            System.out.println(v);
        });

    }





}
