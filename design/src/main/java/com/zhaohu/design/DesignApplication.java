package com.zhaohu.design;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;

@SpringBootApplication
public class DesignApplication {

    public static void main(String[] args) {
        BeanFactory beanFactory=new FileSystemXmlApplicationContext();
        beanFactory=new ClassPathXmlApplicationContext();
         beanFactory.getBean("hh");
        SpringApplication.run(DesignApplication.class, args);
    }

}
