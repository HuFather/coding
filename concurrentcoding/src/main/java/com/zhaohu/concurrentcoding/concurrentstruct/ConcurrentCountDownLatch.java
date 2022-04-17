package com.zhaohu.concurrentcoding.concurrentstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentCountDownLatch {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        CountDownLatch driver = new CountDownLatch(1);
//        CountDownLatch passenger = new CountDownLatch(3);
//
//        Driver driver1 = new Driver(driver, passenger);
//        driver1.doWork();

//        CountDownNum num = new CountDownNum(1001, 10);
//        num.calculate();
    }
}

class Driver {
    CountDownLatch driverLatch;
    CountDownLatch passengerLatch;

    public Driver(CountDownLatch driverLatch, CountDownLatch passengerLatch) {
        this.driverLatch = driverLatch;
        this.passengerLatch = passengerLatch;
    }

    public void doWork() throws InterruptedException {
        driverLatch.countDown();

        System.out.println("准备上车");
        for (int i = 0; i < 3; i++) {
            new Thread(new Passenger(driverLatch, passengerLatch)).start();
        }
        passengerLatch.await();

        System.out.println("准备出发");
    }
}

class Passenger implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    CountDownLatch driverLatch;
    CountDownLatch passengerLatch;

    public Passenger(CountDownLatch driverLatch, CountDownLatch passengerLatch) {
        this.driverLatch = driverLatch;
        this.passengerLatch = passengerLatch;
    }

    public void doWork() throws InterruptedException {
        driverLatch.await();
        System.out.println("上车完成");
        passengerLatch.countDown();
    }
}

class CountDownNum {
    int num;
    int time;

    public CountDownNum(int num, int time) {
        this.num = num;
        this.time = time;
    }

    public int calculate() throws InterruptedException, ExecutionException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(10);
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 1000, TimeUnit.MILLISECONDS, blockingQueue);


        CountDownLatch calculateLatch = new CountDownLatch(time + 1);
        int size = num / time;
        List<Future<Integer>> result=new ArrayList<>();
        for (int i = 0; i < time + 1; i++) {
            int end = i != time ? (i + 1) * size : num + 1;
            Future<Integer> future = executorService.submit(new Worker(i * size, calculateLatch, end));
            result.add(future);

        }

        calculateLatch.await();
        System.out.println("计算结束");
        Long resultNum=0L;
        for(Future<Integer> future:result){
            resultNum+=future.get();
        }
        System.out.println("total:"+resultNum);
        return 0;
    }
}

class Worker implements Callable<Integer> {
    int start, end;
    CountDownLatch calculateLatch;

    public Worker(int start, CountDownLatch latch, int end) {
        this.start = start;
        calculateLatch = latch;
        this.end = end;
    }


    @Override
    public Integer call() throws Exception {
        int count = 0;
        for (int i = start; i < end; i++) {
            count += i;
        }
        Thread.sleep(1000);
        System.out.println("count:" + count);
        calculateLatch.countDown();
        return count;
    }
}