package com.zhaohu.design.currentlimiting.rule;

import com.zhaohu.design.currentlimiting.utils.YAMLUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @creator : zhaohu
 * @date : 6/9/2022
 * @description :
 */
public class ApiLimitRule {

    private static UniformRuleConfigMap ruleConfigMap;

    static {
        try (InputStream inputStream=ApiLimitRule.class.getClassLoader().getResourceAsStream("application.yml")){
            ruleConfigMap= YAMLUtils.parse(inputStream, UniformRuleConfigMap.class);
        } catch (IOException exception) {
            throw  new RuntimeException("读取配置文件失败",exception);
        }
    }

    public static UniformRuleConfigMap getRules(){
        return ruleConfigMap;
    }

    public static boolean limit(String app_id,String api){
        return LimitRule.limited(app_id,api);
    }

}
