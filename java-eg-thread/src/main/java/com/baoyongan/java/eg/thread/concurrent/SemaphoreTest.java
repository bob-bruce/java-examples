package com.baoyongan.java.eg.thread.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 信号量  虚拟化了一批许可  可以用来做流控。同时操作某个资源的数量
 */
public class SemaphoreTest<T> {

    // 有界集合
    private final Set<T> set;

    private final Semaphore sem;

    public SemaphoreTest(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        this.sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        }finally {
            if (!wasAdded){
                sem.release();
            }
        }
    }

    public boolean remove(T o){
        boolean wasRemoved=set.remove(o);
        if (wasRemoved)
            sem.release();
        return wasRemoved;
    }

    public static void main(String[] args) {

    }
}
