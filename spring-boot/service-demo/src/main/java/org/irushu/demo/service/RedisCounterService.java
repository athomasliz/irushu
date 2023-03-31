package org.irushu.demo.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCounterService {
    private static Logger logger = LoggerFactory.getLogger(RedisCounterService.class);

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
