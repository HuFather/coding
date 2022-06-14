package com.zhaohu.design.currentlimiting.rule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @creator : zhaohu
 * @date : 6/14/2022
 * @description :
 */
class ApiRateLimitRuleTest {

    private static ApiLimit l1 = new ApiLimit("/", 100, 20);
    private static ApiLimit l2 = new ApiLimit("/user", 90, 10);
    private static ApiLimit l3 = new ApiLimit("/user/lender", 80, 10);
    private static ApiLimit l4 = new ApiLimit("/user/borrower/lpd", 70, 10);
    private static ApiLimit l5 = new ApiLimit("/user/{actorId}/lpd", 60, 10);
    private static ApiLimit l6 = new ApiLimit("/user/{actorId:(.*)}/lpd", 50, 10);

    @Test
    void addLimit() {
        ApiRateLimitRule rule=new ApiRateLimitRule();
        rule.addLimit("app_1",l1);
        rule.addLimit("app_1",l2);
        rule.addLimit("app_1",l3);
        rule.addLimit("app_1",l4);
        rule.addLimit("app_1",l5);
        rule.addLimit("app_1",l6);

        assertNull(rule.getLimit("app_1",""));
        assertSame(rule.getLimit("app_1","/"),l1);
        assertSame(rule.getLimit("app_1","/user/lender"),l3);
        assertSame(rule.getLimit("app_1","/user/123/lpd"),l6);
    }

    @Test
    void getLimit() {
    }

    @Test
    void addLimits() {
    }

    @Test
    void addRule() {
    }

    @Test
    void rebuildRule() {
    }
}