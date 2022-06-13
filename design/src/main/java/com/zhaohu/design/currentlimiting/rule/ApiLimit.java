package com.zhaohu.design.currentlimiting.rule;

/**
 * @creator : zhaohu
 * @date : 6/9/2022
 * @description :
 */

public class ApiLimit {
    private static final int DEFAULT_TIME_UNIT = 1;
    private String api;
    private int limit;
    private int unit = DEFAULT_TIME_UNIT;

    public ApiLimit() {

    }

    public ApiLimit(String api, int limit) {
        this(api, limit, DEFAULT_TIME_UNIT);
    }

    public ApiLimit(String api, int limit, int unit) {
        this.api = api;
        this.limit = limit;
        this.unit = unit;
    }

    String getApi() {
        return this.api;
    }

    public int getLimit() {
        return limit;
    }

    public int getUnit() {
        return unit;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
