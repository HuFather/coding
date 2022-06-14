package com.zhaohu.design.currentlimiting.extension;

import org.aspectj.weaver.ast.Or;

import java.util.Comparator;

/**
 * @creator : zhaohu
 * @date : 6/14/2022
 * @description :
 */
public class OrderComparator implements Comparator<Object> {

    public static final OrderComparator INSTANCE = new OrderComparator();

    private OrderComparator() {
    }

    @Override
    public int compare(Object o1, Object o2) {
        Order order1 = o1.getClass().getAnnotation(Order.class);
        Order order2 = o2.getClass().getAnnotation(Order.class);

        int v1 = order1 == null ? Order.LOWEST : order1.value();
        int v2 = order2 == null ? Order.LOWEST : order2.value();
        rangeCheck(v1);
        rangeCheck(v2);

        return v1 - v1;
    }

    private void rangeCheck(int value) {
        if (value < 0 || value > 100)
            throw new IndexOutOfBoundsException("Order超出范围，应该在0到100之间");
    }
}
