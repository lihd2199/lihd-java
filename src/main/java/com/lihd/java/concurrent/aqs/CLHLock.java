package com.lihd.java.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: li_hd
 * @date: 2020-09-10 14:09
 **/
public class CLHLock implements Lock {

    AtomicReference<QueueNode> tail = new AtomicReference<>(new QueueNode());

    ThreadLocal<QueueNode> pred;
    ThreadLocal<QueueNode> current;

    public CLHLock() {
        current = ThreadLocal.withInitial(QueueNode::new);
        pred = ThreadLocal.withInitial(() -> null);
    }


    @Override
    public void lock() {
        QueueNode node = current.get();
        node.locked = true;
        QueueNode pred = tail.getAndSet(node);
        this.pred.set(pred);
        while (pred.locked) {

        }

    }

    @Override
    public void lockInterruptibly() {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        return false;
    }

    @Override
    public void unlock() {
        QueueNode node = current.get();
        node.locked = false;
        current.set(this.pred.get());
    }

    @Override
    public Condition newCondition() {
        return null;
    }


    static class QueueNode {
        boolean locked;
    }

}
