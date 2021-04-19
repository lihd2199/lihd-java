package com.lihd.java.concurrent.reentrantreadwritelock;

import com.lihd.java.concurrent.executors.ExecutorsTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lihd
 * @desc 写锁
 */
public class WriteLock {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private Map map = new HashMap();

    public static void main(String[] args) throws InterruptedException {

        WriteLock writeLock = new WriteLock();
        Runnable runnable1 = writeLock::read;
        for(int i=0;i<3;i++){
            int integer = i;
            Runnable runnable2 = () -> writeLock.write(integer);
            ExecutorsTest.getInstance().execute(runnable2);
            Thread.sleep(1000);
            ExecutorsTest.getInstance().execute(runnable1);
        }
        ExecutorsTest.getInstance().shutdown();

    }




    private void read(){

        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"-获取读锁!");
            System.out.println(map.get("abc"));
        }finally {
            System.out.println(Thread.currentThread().getName()+"-释放读锁!");
            lock.readLock().unlock();
        }

    }


    private void write(Integer integer){

        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"-获取写锁!");
            Thread.sleep(2000);
            map.put("abc",integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"-释放写锁!");
            lock.writeLock().unlock();
        }

    }



}
