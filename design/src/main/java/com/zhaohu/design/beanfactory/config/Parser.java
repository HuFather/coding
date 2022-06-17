package com.zhaohu.design.beanfactory.config;

import com.zhaohu.design.beanfactory.config.entity.BeanDefine;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.util.List;

/**
 * @creator : zhaohu
 * @date : 6/17/2022
 * @description :
 */
public interface Parser {
    List<BeanDefine> parser(String text);

    List<BeanDefine> parser(InputStream inputStream);
}
