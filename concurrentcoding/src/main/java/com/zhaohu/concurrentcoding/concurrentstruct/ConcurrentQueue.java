package com.zhaohu.concurrentcoding.concurrentstruct;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConcurrentQueue {
    public static void main(String[] args) {
        BlockingQueue queue=new ArrayBlockingQueue(10);
        queue=new LinkedBlockingQueue();
        String a=":ss";
        a+="dd";
        char c=a.charAt(0);
        System.out.println(a);
    }
}
