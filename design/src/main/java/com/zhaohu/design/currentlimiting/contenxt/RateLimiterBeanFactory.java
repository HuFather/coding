package com.zhaohu.design.currentlimiting.contenxt;

import com.zhaohu.design.currentlimiting.contenxt.loader.PropertySourceLoader;
import com.zhaohu.design.currentlimiting.rule.RateLimitRule;

/**
 * @creator : zhaohu
 * @date : 6/14/2022
 * @description :
 */
public class RateLimiterBeanFactory {
    public static final RateLimiterBeanFactory BEAN_FACTORY=new RateLimiterBeanFactory();

    PropertySourceLoader loader;
    private RateLimiterBeanFactory(){

    }


}
