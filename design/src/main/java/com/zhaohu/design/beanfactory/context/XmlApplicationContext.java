package com.zhaohu.design.beanfactory.context;

import com.sun.jmx.remote.internal.ArrayQueue;
import com.zhaohu.design.beanfactory.ApplicationContext;
import com.zhaohu.design.beanfactory.config.Parser;
import com.zhaohu.design.beanfactory.config.XmlParser;
import com.zhaohu.design.beanfactory.config.entity.BeanDefine;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @creator : zhaohu
 * @date : 6/17/2022
 * @description :
 */
public class XmlApplicationContext implements ApplicationContext {
    private Parser parser;
    BeanFactory beanFactory = new BeanFactory();

    public XmlApplicationContext() {
        this(null);
    }

    public XmlApplicationContext(String xmlPath) {
        parser = new XmlParser();
        load(xmlPath);
    }

    private void load(String xmlPath) {

        List<BeanDefine> list = parser.parser(xmlPath);
        List<BeanDefine> beanDefineList = new ArrayList<>();

        beanFactory.addBeans(beanDefineList);
    }

    @Override
    public Object getBean(String beanId) {
        return beanFactory.getBean(beanId);
    }
}
