package com.zhaohu.concurrentcoding.lockdemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SemaphoreConcurrent {
    public static void main(String[] args) {

    }
    Semaphore semaphore=new Semaphore(1);
    public void doSomeThing() throws InterruptedException {
        semaphore.acquire();
        semaphore.release();

        ReadWriteLock lock=new ReentrantReadWriteLock();
        Lock readLock= lock.readLock();
        readLock.newCondition();
        readLock.unlock();

        CountDownLatch countDownLatch=new CountDownLatch(10);
        countDownLatch.countDown();
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5);

    }
}
