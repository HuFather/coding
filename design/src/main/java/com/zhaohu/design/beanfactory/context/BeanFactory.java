package com.zhaohu.design.beanfactory.context;

import com.zhaohu.design.beanfactory.config.entity.BeanDefine;
import com.zhaohu.design.beanfactory.config.entity.ConstructorArg;
import org.apache.commons.lang.NullArgumentException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

    public void addBeans(List<BeanDefine> beanDefines) {
        for (BeanDefine bean : beanDefines) {
            beans.put(bean.getId(), bean);
            if (bean.isSingleton()) {
                singleBeans.put(bean.getId(), createBean(bean.getId()));
            }
        }

    }

    public Object createBean(String id) {
        BeanDefine beanDefine = beans.get(id);
        if (beanDefine == null)
            throw new NullArgumentException("there is no such bean defined " + id);

        if (singleBeans.containsKey(id))
            return singleBeans.get(id);

        try {
            Class type = Class.forName(beanDefine.getName());
            if (beanDefine.getConstructorArgs().size() == 0)
                return type.newInstance();
            Class[] classes = new Class[beanDefine.getConstructorArgs().size()];
            Object[] objects = new Object[beanDefine.getConstructorArgs().size()];
            List<ConstructorArg> list = beanDefine.getConstructorArgs();
            for (int i = 0; i < list.size(); i++) {
                ConstructorArg arg = list.get(i);
                if (!arg.isRefer()) {
                    classes[i] = arg.getClass();
                    objects[i] = arg.getArg();
                } else {
                    classes[i] = arg.getClass();
                    objects[i] = createBean(arg.getId());
                }
            }

            return type.getConstructor(classes).newInstance(classes);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;

    }

    public Object getBean(String id) {
        BeanDefine beanDefine = beans.get(id);
        if (beanDefine == null)
            throw new NullArgumentException("there is no such bean defined " + id);

        return createBean(id);

    }


}
