package com.boot.dubbo.service.impl;

import com.boot.dubbo.model.Role;
import com.boot.dubbo.model.User;
import com.boot.dubbo.repo.RoleRepo;
import com.boot.dubbo.repo.UserRepo;
import com.boot.dubbo.repo.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 23:53
 * Information:
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查数据库
        User user = userRepo.findUserByUsername(username);
        if (null != user) {
            List<Role> roles = roleRepo.findByUserId(user.getId());
            if (roles!=null&&roles.size()>0) {
                user.setAuthorities(roles);
                return user;
            } else {
                throw new IllegalArgumentException("illegal access");
            }
        }else{
            throw new IllegalArgumentException("account no exist");
        }
    }
}
