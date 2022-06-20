package com.zhaohu.design.beanfactory.config.entity;

import java.lang.reflect.Type;

/**
 * @creator : zhaohu
 * @date : 6/17/2022
 * @description :
 */
public class ConstructorArg {
    private boolean isRefer;
    private Class type;
    private Object arg;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isRefer() {
        return isRefer;
    }

    public void setRefer(boolean refer) {
        isRefer = refer;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Object getArg() {
        return arg;
    }

    public void setArg(Object arg) {
        this.arg = arg;
    }
}
