package com.threadcoding.lock;

/**
 * @creator : zhaohu
 * @date : 4/14/2022
 * @description :
 */
public class DeadLock {


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
