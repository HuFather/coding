package com.zhaohu.design.currentlimiting.rule;

import java.util.ArrayList;
import java.util.List;

/**
 * @creator : zhaohu
 * @date : 6/9/2022
 * @description :
 */
public class UniformRuleConfigMap {

    private List<AppApiLimit> configs;

    public UniformRuleConfigMap() {
        configs = new ArrayList<>();
    }

    public List<AppApiLimit> getConfigs() {
        return configs;
    }

    public void setConfigs(List<AppApiLimit> configs) {
        this.configs = configs;
    }

    public static class AppApiLimit {
        private String app_id;
        private List<ApiLimit> limits;

        public AppApiLimit() {
            limits = new ArrayList<>();
        }

        public AppApiLimit(String app_id, List<ApiLimit> limits) {
            this.app_id = app_id;
            this.limits = limits;
        }

        public String getApp_id() {
            return app_id;
        }

        public List<ApiLimit> getLimits() {
            return limits;
        }


        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public void setLimits(List<ApiLimit> limits) {
            this.limits = limits;
        }

        public void addLimits(List<ApiLimit> limits) {
            this.limits.addAll(limits);
        }

        public void addLimit(ApiLimit limit) {
            limits.add(limit);
        }
    }
}

