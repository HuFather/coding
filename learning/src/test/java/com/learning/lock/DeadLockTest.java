package com.learning.lock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @creator : zhaohu
 * @date : 4/14/2022
 * @description :
 */
@DisplayName("firsttest")
class DeadLockTest {

    @Test
    public void test() throws InterruptedException {
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
}