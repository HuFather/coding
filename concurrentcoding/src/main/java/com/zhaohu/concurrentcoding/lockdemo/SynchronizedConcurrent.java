package com.zhaohu.concurrentcoding.lockdemo;

import java.util.LinkedList;

/**
 * @creator : zhaohu
 * @date : 4/12/2022
 * @description :
 */
public class SynchronizedConcurrent {

    public static void main(String[] args) throws InterruptedException {
        SynchronizedConcurrent synchronizedConcurrent=new SynchronizedConcurrent();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                synchronizedConcurrent.print();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                synchronizedConcurrent.add(i);
            }
        }).start();

        Thread.sleep(10000);
    }
    LinkedList<Integer> data;
    public SynchronizedConcurrent(){
        data= new LinkedList<Integer>();
    }
    Object conditionAdd =new Object();
    public void add(int num){
        synchronized (conditionAdd){
            conditionAdd.notify();
            data.add(num);
            System.out.println("加入："+num);

            try {
                conditionAdd.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void print(){
        synchronized (conditionAdd){
            try {
                conditionAdd.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer result=data.poll();

            if(result!=null)
                System.out.println("取出："+result);
            conditionAdd.notify();
        }
    }

}
