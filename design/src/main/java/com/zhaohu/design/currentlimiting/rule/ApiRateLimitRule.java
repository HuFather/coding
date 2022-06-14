package com.zhaohu.design.currentlimiting.rule;

import com.zhaohu.design.currentlimiting.utils.YAMLUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @creator : zhaohu
 * @date : 6/9/2022
 * @description :
 */
public class ApiRateLimitRule implements RateLimitRule {

    private ConcurrentHashMap<String, TrieRateLimitRule> rules;

    public ApiRateLimitRule() {
        rules = new ConcurrentHashMap<>();
    }

    @Override
    public void addLimit(String appId, ApiLimit apiLimit) {
        if (StringUtils.isEmpty(appId) || apiLimit == null)
            return;

        TrieRateLimitRule trieRateLimitRule = new TrieRateLimitRule(apiLimit);
        TrieRateLimitRule value = rules.putIfAbsent(appId, trieRateLimitRule);
        if (value != null) {
            value.addApiLimit(apiLimit);
        }
    }

    @Override
    public ApiLimit getLimit(String appId, String api) {
        if (StringUtils.isEmpty(api) || StringUtils.isEmpty(appId))
            return null;

        return rules.getOrDefault(appId, new TrieRateLimitRule())
                .getApiLimit(api);
    }

    @Override
    public void addLimits(String appId, List<ApiLimit> apiLimitList) {
        if (StringUtils.isEmpty(appId) || apiLimitList == null)
            return;

        TrieRateLimitRule rule = new TrieRateLimitRule();
        rule.addApiLimits(apiLimitList);

        TrieRateLimitRule oldValue = rules.putIfAbsent(appId, rule);
        if (oldValue != null)
            oldValue.addApiLimits(apiLimitList);

    }

    @Override
    public void addRule(UniformRuleConfigMap uniformRuleConfigMap) {
        if (uniformRuleConfigMap == null)
            return;
        for (UniformRuleConfigMap.AppApiLimit limit : uniformRuleConfigMap.getConfigs()) {
            addLimits(limit.getApp_id(), limit.getLimits());
        }
    }

    @Override
    public void rebuildRule(UniformRuleConfigMap uniformRuleConfigMap) {
        ConcurrentHashMap<String, TrieRateLimitRule> rebuildRules = new ConcurrentHashMap<>();

        List<UniformRuleConfigMap.AppApiLimit> limits = uniformRuleConfigMap.getConfigs();
        for (UniformRuleConfigMap.AppApiLimit limit : limits) {
            String id = limit.getApp_id();
            List<ApiLimit> list = limit.getLimits();

            TrieRateLimitRule rule = new TrieRateLimitRule();
            rule.addApiLimits(list);
            rebuildRules.put(id, rule);
        }

        rules = rebuildRules;
    }
}
