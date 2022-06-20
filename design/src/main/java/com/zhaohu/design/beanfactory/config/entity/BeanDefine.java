package com.zhaohu.design.beanfactory.config.entity;

import java.util.List;

/**
 * @creator : zhaohu
 * @date : 6/17/2022
 * @description :
 */
public class BeanDefine {
    private String id;
    private String name;
    private List<ConstructorArg> constructorArgs;
    private boolean isSingleton;
    private ScopeEnum scopeEnum=ScopeEnum.SCOPE_SINGLETON;
    private Class type;

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public boolean isSingleton() {
        return isSingleton;
    }

    public void setSingleton(boolean singleton) {
        isSingleton = singleton;
    }

    public ScopeEnum getScopeEnum() {
        return scopeEnum;
    }

    public void setScopeEnum(ScopeEnum scopeEnum) {
        this.scopeEnum = scopeEnum;
    }
}
