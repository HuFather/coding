package com.zhaohu.design.currentlimiting.extension;

import com.alibaba.nacos.shaded.com.google.protobuf.Internal;
import jdk.internal.dynalink.beans.StaticClass;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @creator : zhaohu
 * @date : 6/14/2022
 * @description :
 */
public class ExtensionLoader {

    private static final ConcurrentHashMap<Class<?>,Object> CLASS_LOCKS=new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Class<?>,List<?>> SINGLE_OBJECTS=new ConcurrentHashMap<>();
    public static <T> T getExtension(Class<T> tClass) {
        return getExtension(tClass,false);
    }

    public static <T> T getExtension(Class<T> tClass, boolean isSingleton) {
        List<T> serverList =  getExtensions(tClass,isSingleton);
        if(serverList==null || serverList.isEmpty())
            return null;

        return serverList.get(0);
    }

    public static <T> List<T> getExtensions(Class<T> tClass,boolean isSingleton){
        if(isSingleton){
            List<T> extensions= (List<T>) SINGLE_OBJECTS.get(tClass);
            if(extensions!=null && !extensions.isEmpty())
                return extensions;
        }
        synchronized (getLock(tClass)){
            if(!isSingleton){
                return load(tClass);
            }

            if(SINGLE_OBJECTS.containsKey(tClass))
                return (List<T>) SINGLE_OBJECTS.get(tClass);

            List<T> serverList=load(tClass);
            SINGLE_OBJECTS.put(tClass,serverList);

            return serverList;
        }
    }

    public static <T> List<T> load(Class<T> tClass) {
        List<T> serverList = new ArrayList<>();
        ServiceLoader<T> load = ServiceLoader.load(tClass);
        for (T service : load) {
            serverList.add(service);
        }

        if (!serverList.isEmpty()) {
            Collections.sort(serverList, OrderComparator.INSTANCE);
        }
        return serverList;
    }

    private static Object getLock(Class<?> tClass){
        Object lock=CLASS_LOCKS.get(tClass);
        if(lock==null){
            lock=new Object();
            CLASS_LOCKS.put(tClass,lock);
        }

        return lock;
    }

}
