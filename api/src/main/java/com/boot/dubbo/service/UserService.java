package com.boot.dubbo.service;

import com.boot.dubbo.model.User;
import com.boot.dubbo.model.UserAddress;

import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-02.
 * Time: 23:10
 * Information:
 */
public interface UserService {

    List<UserAddress> getUserAddressList(String userId);


}
