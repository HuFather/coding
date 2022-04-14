package com.zhaohu.concurrentcoding.lockdemo;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @creator : zhaohu
 * @date : 4/12/2022
 * @description :
 */
public class ReentrantConcurrent {

    public static void main(String[] args) throws InterruptedException {
        ReentrantConcurrent reentrantConcurrent = new ReentrantConcurrent();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    reentrantConcurrent.add(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 11; i++) {
                try {
                    reentrantConcurrent.print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(10000);
    }

    LinkedList<Integer> data;

    ReentrantLock lock;
    Condition addCondition;
    Condition printCondition;

    public ReentrantConcurrent() {
        data = new LinkedList<>();
        lock = new ReentrantLock();
        addCondition = lock.newCondition();
        printCondition = lock.newCondition();
    }

    public void add(int num) throws InterruptedException {
        lock.lock();
        addCondition.await();
        data.add(num);
        System.out.println("输入" + num);
        printCondition.signal();
        lock.unlock();

        lock.getHoldCount();

    }

    public void print() throws InterruptedException {
        lock.lock();
        addCondition.signal();
        Integer result = data.poll();
        if (result != null)
            System.out.println("输出" + result);
        printCondition.await();
        lock.unlock();
    }
}
