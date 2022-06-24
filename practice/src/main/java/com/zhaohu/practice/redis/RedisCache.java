package com.zhaohu.practice.redis;

import com.zhaohu.practice.redis.entity.User;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @creator : zhaohu
 * @date : 6/22/2022
 * @description :
 */
@Service
public class RedisCache {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedissonClient redisson;

    @Autowired
    private RBloomFilter bloomFilter;

    public String addUser(String userId, User user) {
        stringRedisTemplate.opsForValue().set(userId, user.getName());
        return userId;
    }

    public String addUserObj(String userId, User user) {
        redisTemplate.opsForValue().set(userId, user);
        return userId;
    }

    public User getUser(String userId) {
        User o = (User) redisTemplate.opsForValue().get(userId);
        return o;
    }

    public void addUsers(String group, List<User> userList) {
        redisTemplate.opsForList().leftPushAll("t", userList);
        List<User> t = (List<User>) (List<?>) redisTemplate.opsForList().range("t", 0, -1);

    }

    public void addSet() {
        User user = new User();
        user.setId(2);
        redisTemplate.opsForSet().add("user", user);
        user.setName("dd");
        redisTemplate.opsForSet().add("user", user);
        redisTemplate.convertAndSend("my", "hello");

    }


    public boolean lock(String lock) {
        boolean result = redisTemplate.opsForValue().setIfAbsent(lock, 12, 10, TimeUnit.SECONDS);
        if (result) {
            String scrip = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
            RedisScript script = new DefaultRedisScript(scrip, Long.class);
            redisTemplate.execute(script, Arrays.asList("lock"), 12);
        }

        return false;
    }

    public void lockTest() {
        RLock lock = redisson.getLock("lock");
        lock.lock(30, TimeUnit.SECONDS);
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public boolean bloomFilter() {
        bloomFilter.add(1);
        return bloomFilter.contains(1);
    }

    public boolean bloomfilter1(){
        return bloomFilter.contains(2);
    }


}
