package com.zhaohu.design.beanfactory.context;

import com.zhaohu.design.beanfactory.config.entity.BeanDefine;
import org.apache.commons.lang.NullArgumentException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @creator : zhaohu
 * @date : 6/17/2022
 * @description :
 */
public class BeanFactory {
    private Map<String, BeanDefine> beans = new LinkedHashMap<>();
    private Map<String, Object> singleBeans = new LinkedHashMap<>();

    public void addBeans(List<BeanDefine> beanDefines){
        for (BeanDefine bean:beanDefines){
            beans.put(bean.getId(),bean);
            if(bean.isSingleton()){
                singleBeans.put(bean.getId(),createBean(bean.getId()));
            }
        }

    }

    public Object createBean(String id){
        BeanDefine beanDefine=beans.get(id);
        if(beanDefine==null)
            throw new NullArgumentException("there is no such bean defined "+id);

        if(singleBeans.containsKey(id))
            return singleBeans.get(id);



        return null;

    }

    public Object getBean(String id){
        BeanDefine beanDefine=beans.get(id);
        if(beanDefine==null)
            throw new NullArgumentException("there is no such bean defined "+id);

        return createBean(id);

    }


}
