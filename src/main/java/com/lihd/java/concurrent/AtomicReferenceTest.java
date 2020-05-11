package com.lihd.java.concurrent;

import lombok.Data;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-11 16:44
 **/
public class AtomicReferenceTest {


    @Test
    public void test() {
        AtomicReference<People> atomicReference = new AtomicReference<>();

        final People people = new People();
        people.setId("1");
        people.setName("lihd");
        atomicReference.set(people);

        final boolean flag = atomicReference.compareAndSet(people, new People());

        System.out.println(flag);
    }


    @Data
    private static class People {

        private String id;

        private String name;

        @Override
        public String toString() {
            return "People{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public int hashCode() {
            return 1;
        }

    }

}
