package com.zhaohu.design.currentlimiting.rule;

/**
 * @creator : zhaohu
 * @date : 5/13/2022
 * @description :
 */
public interface RateLimitRuleService {
    ApiRateLimitRule getLimit(String appId, String api);

}
