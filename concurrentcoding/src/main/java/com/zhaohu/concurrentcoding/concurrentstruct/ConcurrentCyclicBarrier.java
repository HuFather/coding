package com.zhaohu.concurrentcoding.concurrentstruct;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ConcurrentCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier barrier=new CyclicBarrier(3,()->{
            System.out.println("结束");
        });

        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                System.out.println("执行...");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
