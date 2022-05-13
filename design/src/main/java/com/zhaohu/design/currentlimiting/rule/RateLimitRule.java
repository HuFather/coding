package com.zhaohu.design.currentlimiting.rule;

import lombok.Data;

import java.util.List;

/**
 * @creator : zhaohu
 * @date : 5/13/2022
 * @description :
 */
@Data
public class RateLimitRule {
    private String appId;
    private List<ApiRateLimitRule> limits;
}
