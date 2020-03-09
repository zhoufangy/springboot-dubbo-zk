package com.boot.dubbo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 02:13
 * Information:
 */

@Api(description = "redis数据",position = 0)
@RestController
@RequestMapping("/api/")
public class RedisController {
    final static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "set",tags = "redis")
    @RequestMapping(value = "set",method = RequestMethod.GET)
    public Object set( String key, String value){
        stringRedisTemplate.opsForValue().set(key, value);
        return "成功";
    }
    @ApiOperation(value = "get",tags = "redis")
    @RequestMapping(value = "get/{key}",method = RequestMethod.POST)
    public Object get(@PathVariable("key") String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
}
