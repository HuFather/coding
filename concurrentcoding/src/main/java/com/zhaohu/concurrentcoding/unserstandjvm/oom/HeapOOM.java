package com.zhaohu.concurrentcoding.unserstandjvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

    public static void main(String[] args) throws InterruptedException {
        List<HeapOOM> list=new ArrayList<>();
        while (true){
//            Thread.sleep(1);
            list.add(new HeapOOM());
        }
    }
}
