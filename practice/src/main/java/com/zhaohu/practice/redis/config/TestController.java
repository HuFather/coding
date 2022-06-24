package com.zhaohu.practice.redis.config;

import com.zhaohu.practice.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @creator : zhaohu
 * @date : 6/23/2022
 * @description :
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private RedisCache redisCache;

}
