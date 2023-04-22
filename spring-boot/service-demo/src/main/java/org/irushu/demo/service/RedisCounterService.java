package org.irushu.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisCounterService {

    @Autowired
    private RedisTemplate redisTemplate;

    public Long count(String key){
        Long value = (Long) redisTemplate.opsForValue().get(key);
        if(value == null){
            value = 1L;
        }
        else{
            value = value + 1;
        }
        redisTemplate.opsForValue().set(key, value);
        return value;
    }

}
