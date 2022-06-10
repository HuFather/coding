package com.zhaohu.design.currentlimiting.rule.parse;

import com.zhaohu.design.currentlimiting.rule.UniformRuleConfigMap;

import java.io.InputStream;

/**
 * @creator : zhaohu
 * @date : 6/10/2022
 * @description :
 */
public interface RuleConfigParse {
    UniformRuleConfigMap parse(InputStream inputStream);

    UniformRuleConfigMap parse(String configStr);
}
