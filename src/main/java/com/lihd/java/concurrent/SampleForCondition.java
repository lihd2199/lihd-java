package com.lihd.java.concurrent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: lihd-java
 * @description: sample for condition
 * @author: li_hd
 * @create: 2019-12-23 14:37
 **/
public class SampleForCondition {


    public static void main(String[] args) throws InterruptedException {

        SharedFiFoQueue sharedQueue = new SharedFiFoQueue(10);

        //Create a producer and a consumer.
        Thread producer = new Producer(sharedQueue);
        Thread consumer = new Consumer(sharedQueue);

        //Start both threads.
        producer.start();
        consumer.start();

        //Wait for both threads to terminate.
        producer.join();
        consumer.join();
    }


}

class SharedFiFoQueue {


    private Object[] elems = null;
    private int current = 0;
    private int placeIndex = 0;
    private int removeIndex = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition isEmpty = lock.newCondition();
    private final Condition isFull = lock.newCondition();

    public SharedFiFoQueue(int capacity) {
        this.elems = new Object[capacity];
    }

    public void add(Object elem) throws InterruptedException {
        lock.lock();
        while (current >= elems.length)
            isFull.await();

        elems[placeIndex] = elem;

        //We need the modulo, in order to avoid going out of bounds.
        placeIndex = (placeIndex + 1) % elems.length;

        ++current;

        //Notify the consumer that there is data available.
        isEmpty.signal();

        lock.unlock();
    }

    public Object remove() throws InterruptedException {
        Object elem = null;

        lock.lock();
        while (current <= 0)
            isEmpty.await();

        elem = elems[removeIndex];

        //We need the modulo, in order to avoid going out of bounds.
        removeIndex = (removeIndex + 1) % elems.length;

        --current;

        //Notify the producer that there is space available.
        isFull.signal();

        lock.unlock();

        return elem;
    }


}


class Consumer extends Thread {
    private final Set<Object> seenObjects = new HashSet<>();
    private int total = 0;
    private final SharedFiFoQueue queue;

    public Consumer(SharedFiFoQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            do {
                Object obj = queue.remove();
                if(obj == null)
                    break;

                if(!seenObjects.contains(obj)) {
                    ++total;
                    seenObjects.add(obj);
                }

                System.out.println("[Consumer] Read the element: " + obj.toString());

            } while(true);
        }
        catch (InterruptedException ex) {
            System.err.println("An InterruptedException was caught: " + ex.getMessage());
            ex.printStackTrace();
        }

        System.out.println("\n[Consumer] " + total + " distinct words have been read...");
    }
}




class Producer extends Thread {

    private final static String FILENAME = "input.txt";

    private final SharedFiFoQueue queue;

    public Producer(SharedFiFoQueue queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        BufferedReader rd = null;

        try {
            rd = new BufferedReader(new FileReader(new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource(FILENAME)).toURI())));

            String inputLine;
            while ((inputLine = rd.readLine()) != null) {
                String[] inputWords = inputLine.split(" ");

                for (String inputWord : inputWords)
                    queue.add(inputWord);
            }

            //Terminate the execution.
            queue.add(null);
        } catch (InterruptedException ex) {
            System.err.println("An InterruptedException was caught: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("An IOException was caught: " + ex.getMessage());
            ex.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rd != null)
                    rd.close();
            } catch (IOException ex) {
                System.err.println("An IOException was caught: " + ex.getMessage());
                ex.printStackTrace();
            }
        }

    }
}
