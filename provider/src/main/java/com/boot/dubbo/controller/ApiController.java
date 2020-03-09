package com.boot.dubbo.controller;

import com.boot.dubbo.model.UserAddress;
import com.boot.dubbo.repo.UserAddressRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 02:13
 * Information:
 */
@RestController
@RequestMapping("/api/")
public class ApiController {
    final static Logger logger = LoggerFactory.getLogger(ApiController.class);


    @Autowired
    private UserAddressRepo userAddressRepo;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "user",method = RequestMethod.GET)
    public Object method(){
        List<UserAddress> list = userAddressRepo.findByUserId("1");
        return list;
    }

    @RequestMapping(value = "set",method = RequestMethod.GET)
    public Object set(){
        stringRedisTemplate.opsForValue().set("nihao", "Hello Redis");
        return "成功";
    }
    @RequestMapping(value = "get",method = RequestMethod.GET)
    public Object get(){
        return stringRedisTemplate.opsForValue().get("nihao");
    }
}
