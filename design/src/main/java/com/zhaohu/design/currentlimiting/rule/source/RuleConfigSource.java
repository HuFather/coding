package com.zhaohu.design.currentlimiting.rule.source;

import com.zhaohu.design.currentlimiting.rule.UniformRuleConfigMap;

/**
 * @creator : zhaohu
 * @date : 6/10/2022
 * @description :
 */
public interface RuleConfigSource {
    UniformRuleConfigMap load();
}
