package com.zhaohu.design.currentlimiting.contenxt;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 存储配置参数
 * @creator : zhaohu
 * @date : 6/16/2022
 * @description :
 */
public class PropertySource {
    private Map<String ,Object> properties=new LinkedHashMap<>();

    public PropertySource(){

    }

    public PropertySource(Map<String ,Object> map){
        properties=new LinkedHashMap<>(map);
    }

    public Map<String ,Object> getProperties(){
        return properties;
    }

}
