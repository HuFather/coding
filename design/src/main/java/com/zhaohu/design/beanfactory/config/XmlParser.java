package com.zhaohu.design.beanfactory.config;

import com.zhaohu.design.beanfactory.config.entity.BeanDefine;

import java.io.InputStream;
import java.util.List;

/**
 * @creator : zhaohu
 * @date : 6/17/2022
 * @description :
 */
public class XmlParser implements Parser{
    @Override
    public List<BeanDefine> parser(String text) {
        return null;
    }

    @Override
    public List<BeanDefine> parser(InputStream inputStream) {
        return null;
    }
}
