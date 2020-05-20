package com.lihd.java.reference;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.ref.WeakReference;

/**
 * @program: lihd-java
 * @description: Sample For Weak Reference
 * @author: li_hd
 * @create: 2020-05-13 11:08
 **/
public class SampleForWeakReference {

    public void sample() {

        final Product product = new Product("lihd");

        final WeakReference<Product> weakReference = new WeakReference<>(product);

        int i = 0;
        while (true) {
            if (weakReference.get() != null) {
                i++;
                System.out.println("Object is alive for " + i + " loops - " + weakReference);
            } else {
                System.out.println("Object has been collected.");
                break;
            }
        }

    }


    @Data
    @AllArgsConstructor
    private static class Product {

        private String name;

    }


}
