package com.zhaohu.design.beanfactory;

/**
 * @creator : zhaohu
 * @date : 6/17/2022
 * @description :
 */
public interface ApplicationContext {
    public Object getBean(String beanId);
}
