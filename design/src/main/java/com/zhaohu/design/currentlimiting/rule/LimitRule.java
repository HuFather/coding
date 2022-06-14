package com.zhaohu.design.currentlimiting.rule;

import com.sun.org.apache.xml.internal.utils.Trie;
import org.apache.commons.lang3.time.StopWatch;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @creator : zhaohu
 * @date : 6/10/2022
 * @description :
 */
public class LimitRule {


    private static StopWatch stopWatch;
    private static Map<String,Integer> counter;
    private static Map<String, Trie> rules;
    private LimitRule(){}
    static {
        rules=new HashMap<>();
        UniformRuleConfigMap ruleMap =null;// ApiRateLimitRule.getRules();
        for(UniformRuleConfigMap.AppApiLimit rule:ruleMap.getConfigs()){
            Trie trie=new Trie();
            for(ApiLimit apiLimit: rule.getLimits()) {
                trie.put(apiLimit.getApi(),apiLimit);
            }
            rules.put(rule.getApp_id(),trie);
        }

        counter=new HashMap<>();
        stopWatch=new StopWatch();
        stopWatch.start();
    }

    public static boolean limited(String app_id,String url){

        ApiLimit apiLimit= (ApiLimit) rules.get(app_id).get(url);
        if(stopWatch.getTime(TimeUnit.MILLISECONDS)>apiLimit.getUnit()){
            System.out.println("重置时间："+stopWatch.getTime(TimeUnit.MILLISECONDS));
            stopWatch.reset();
            stopWatch.start();

            counter.put(app_id+url,1);
            return true;
        }else {
            if(counter.getOrDefault(app_id+url,0)>apiLimit.getLimit()){
                return false;
            }
            else {
                counter.compute(app_id+url,(k,v)->{
                    if(v==null){
                        return 1;
                    }
                    else {
                        return v+1;
                    }
                });
                System.out.println("个数: "+counter.get(app_id+url));
                System.out.println("当前时间: "+stopWatch.getTime(TimeUnit.MILLISECONDS));
                return true;
            }
        }
    }
}
