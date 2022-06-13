package com.zhaohu.design.currentlimiting.rule;

import java.util.List;

/**
 * @creator : zhaohu
 * @date : 6/13/2022
 * @description : 构造出用以查询的配置类接口
 */
public interface RateLimitRule {
    public void addLimit(String appId, ApiLimit apiLimit);

    public ApiLimit getLimit(String appId,String api);

    public void addLimits(String appId, List<ApiLimit> apiLimitList);

    public void addRule(UniformRuleConfigMap uniformRuleConfigMap);

    public void rebuildRule(UniformRuleConfigMap uniformRuleConfigMap);
}
