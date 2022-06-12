package com.zhaohu.design.currentlimiting.rule.parse;

import com.zhaohu.design.currentlimiting.rule.UniformRuleConfigMap;
import com.zhaohu.design.currentlimiting.utils.YAMLUtils;

import java.io.InputStream;

/**
 * @creator : zhaohu
 * @date : 6/10/2022
 * @description :
 */
public class YamlRuleConfigParse implements RuleConfigParse {

    @Override
    public UniformRuleConfigMap parse(InputStream inputStream) {
        return YAMLUtils.parse(inputStream,UniformRuleConfigMap.class);
    }

    @Override
    public UniformRuleConfigMap parse(String configStr) {
        return YAMLUtils.parse(configStr,UniformRuleConfigMap.class);
    }
}
