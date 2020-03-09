package com.boot.dubbo.service.impl;

import com.boot.dubbo.repo.UserAddressRepo;
import com.boot.dubbo.model.UserAddress;
import com.boot.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-02.
 * Time: 23:22
 * Information:
 */

@Service(version = "1.0.0")
@Component
public class UserServiceImpl implements UserService {
    final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserAddressRepo userAddressRepo;

    public List<UserAddress> getUserAddressList(String userId) {
        List<UserAddress> addresses = userAddressRepo.findByUserId(userId);
        return addresses;
    }
}
