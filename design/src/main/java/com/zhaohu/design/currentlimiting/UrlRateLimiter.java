package com.zhaohu.design.currentlimiting;

/**
 * @creator : zhaohu
 * @date : 6/13/2022
 * @description :
 */
public interface UrlRateLimiter {
    public void limit(String app,String url);

}
