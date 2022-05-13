package com.zhaohu.design.currentlimiting.rule;

import lombok.Data;

/**
 * @creator : zhaohu
 * @date : 5/13/2022
 * @description :
 */
@Data
public class ApiRateLimitRule {
    private static final int DEFAULT_UNIT = 1;
    private String api;
    private int limit;
    private int unit = DEFAULT_UNIT;
}
