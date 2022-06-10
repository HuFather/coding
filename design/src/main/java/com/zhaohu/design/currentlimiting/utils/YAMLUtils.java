package com.zhaohu.design.currentlimiting.utils;

import com.zhaohu.design.currentlimiting.exception.ConfigurationParseException;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @creator : zhaohu
 * @date : 6/9/2022
 * @description :
 */
public class YAMLUtils {
    public static <T> T parse(InputStream inputStream, Class<T> tClass) {
        if (inputStream == null)
            return null;

        Yaml yaml = new Yaml();
        try {
            return yaml.loadAs(inputStream, tClass);
        } catch (Exception ex) {
            throw new ConfigurationParseException("解析配置文件失败");
        }
    }

    public static <T> T parse(String yamlString, Class<T> tClass) {
        if (yamlString == null || yamlString.isEmpty())
            return null;
        Yaml yaml = new Yaml();
        try {
            return yaml.loadAs(yamlString, tClass);
        } catch (Exception exception) {
            throw new ConfigurationParseException("解析配置文件失败");
        }
    }
}
