package com.threadcoding.ThreadPool;

import java.util.concurrent.*;

/**
 * @creator : zhaohu
 * @date : 4/13/2022
 * @description :
 */
public class ExecutorsPool {
    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(2);
        service.submit(()->{

        });
        ArrayBlockingQueue queue=new ArrayBlockingQueue(10);

        new ThreadPoolExecutor(2,5,6000, TimeUnit.MILLISECONDS,queue);

    }
}
