package com.zhaohu.practice.redis;

import com.zhaohu.practice.PracticeApplication;
import com.zhaohu.practice.redis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @creator : zhaohu
 * @date : 6/23/2022
 * @description :
 */
@SpringBootTest(classes = PracticeApplication.class)
class RedisCacheTest {

    @Autowired
    private RedisCache redisCache;

    @Test
    void addUser(){
        User user=new User();
        user.setId(1);
        user.setName("wang xxx");
        redisCache.addUser(String.valueOf(user.getId()),user);
        redisCache.addUserObj(String.valueOf(user.getId()),user);
    }

    @Test
    void getUser(){
        User user= redisCache.getUser("1");
        assertNotNull(user);
        assertEquals(1,user.getId());
    }

    @Test
    void getUsers(){
        List<User> users=new ArrayList<>();
        User user=new User();
        user.setId(1);
        user.setName("dd");
        users.add(user);
        redisCache.addUsers("t",users);
    }

    @Test
    void addSet(){
        redisCache.addSet();
    }

    @Test
    void lock(){
        redisCache.lockTest();
    }

    @Test
    void bloom(){
        boolean result=redisCache.bloomFilter();
        assertEquals(true,result);
        result=redisCache.bloomfilter1();
        assertEquals(false,result);
    }
}