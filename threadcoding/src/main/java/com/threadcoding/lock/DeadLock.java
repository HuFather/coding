package com.threadcoding.lock;

/**
 * @creator : zhaohu
 * @date : 4/14/2022
 * @description :
 */
public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        DeadLock deadLock=new DeadLock();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                deadLock.actionFirst();
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                deadLock.actionSecond();
            }
        }).start();

        Thread.sleep(100000);
    }

    Object lockObj1=new Object();
    Object lockObj2=new Object();

    public void actionFirst(){
        synchronized (lockObj1){
            System.out.println(Thread.currentThread().getName()+"锁定 obj1");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockObj2){
                System.out.println(Thread.currentThread().getName()+"锁定obj2");
            }
        }
    }

    public void actionSecond(){
        synchronized (lockObj2){
            System.out.println(Thread.currentThread().getName()+"锁定 obj2");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockObj1){
                System.out.println(Thread.currentThread().getName()+"锁定obj1");
            }
        }
    }
}
