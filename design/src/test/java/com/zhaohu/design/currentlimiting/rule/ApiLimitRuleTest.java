package com.zhaohu.design.currentlimiting.rule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @creator : zhaohu
 * @date : 6/9/2022
 * @description :
 */
class ApiLimitRuleTest {

    @Test
    void getRules() {

        UniformRuleConfigMap rules = ApiLimitRule.getRules();
        assertNotNull(rules);
    }

    @Test
    void limit() {
        for (int i = 0; i < 10000; i++) {
            boolean result= ApiLimitRule.limit("first","/home/hello");
            assertTrue(result);
        }
    }
}