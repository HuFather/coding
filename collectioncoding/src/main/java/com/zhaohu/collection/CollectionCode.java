package com.zhaohu.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionCode {
    public static void main(String[] args) {
        Collection<String > collection=new ArrayList<>();

        //List
        //ArrayList
        ArrayList<String > list=new ArrayList<>();
        list.add("1");
        list.add("2");
//        System.out.println(list);

        for (String item: list
             ) {
            if(item.equals("1")){
                list.remove(item);
            }
        }
//        System.out.println(list);
        Iterator<String> iterator = list.iterator();
        iterator.remove();
    }
}
