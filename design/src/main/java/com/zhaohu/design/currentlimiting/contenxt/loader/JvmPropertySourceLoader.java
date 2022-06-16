package com.zhaohu.design.currentlimiting.contenxt.loader;

import com.zhaohu.design.currentlimiting.contenxt.PropertyConstants;
import com.zhaohu.design.currentlimiting.contenxt.PropertySource;

import java.util.*;

/**
 * @creator : zhaohu
 * @date : 6/16/2022
 * @description :
 */
public class JvmPropertySourceLoader implements PropertySourceLoader {
    @Override
    public PropertySource load() {
        HashMap<String, Object> jvmProperties = new HashMap<>();
        Properties properties = System.getProperties();
        Set<String> names = properties.stringPropertyNames();
        for (String name : names) {
            if (name.startsWith(PropertyConstants.PROPERTY_PREFIX)) {
                jvmProperties.put(name, properties.getProperty(name));
            }
        }

        return new PropertySource(jvmProperties);
    }
}
