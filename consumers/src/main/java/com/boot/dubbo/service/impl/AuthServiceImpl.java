package com.boot.dubbo.service.impl;


import com.boot.dubbo.model.*;
import com.boot.dubbo.model.VO.UserVO;
import com.boot.dubbo.repo.*;
import com.boot.dubbo.service.AuthService;
import com.boot.dubbo.utils.BaseTool;
import com.boot.dubbo.utils.JwtTokenUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 17:42
 * Information:
 */

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PermissionRepo pmRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private RolePermissionRepo rolePmRepo;


    @Override
    public String login(String username, String password) {
        //bind spring-boot-security
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //generate new jwtToken
        String token = jwtTokenUtil.generateToken((UserDetails) authentication.getDetails());
        return token;
    }

    @Override
    public User insertUser(String username, String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = new User()
                .setId(BaseTool.getUUID()).setPassword(password).setUsername(username).setIsDelete(0);
        try {
            userRepo.save(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateUser(String userId, String username, String password,Integer isDelete) {
        User user = findUser(userId);
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (user != null) {
            user.setId(userId).setUsername(username).setPassword(password).setIsDelete(isDelete);
            try {
                userRepo.save(user);
                return 1;
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public List<User> getUserList() {
        return userRepo.findByIsDelete(0);
    }

    private User findUser(String userId) {
        return userRepo.findById(userId).get();
    }

    ;

    @Override
    public Permission insertPm(String url, String description, String name, Integer pid) {
        Permission permission = new Permission();
        permission.setId(BaseTool.getUUID()).setName(name).setDescription(description).setPid(pid).setUrl(url).setIsDelete(0);
        try {
            pmRepo.save(permission);
            return permission;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updatePm(String pmId, String url, String description, String name, Integer pid,Integer isDelete) {
        Permission permission = findPm(pmId);
        if (permission!=null){
            permission.setId(pmId).setUrl(url).setPid(pid).setDescription(description).setName(name).setIsDelete(isDelete);
            pmRepo.save(permission);
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public List<Permission> getPmList() {
        return pmRepo.findByIsDelete(0);
    }

    Permission findPm(String pmId){
        return pmRepo.findById(pmId).get();
    }

    @Override
    public Role insertRole(String name) {
        Role role = new Role();
        role.setId(BaseTool.getUUID()).setName(name).setIsDelete(0);
        try {
            roleRepo.save(role);
            return role;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateRole(String roleId, String name,Integer isDelete) {
        Role role = findRole(roleId);
        if (role!=null){
            roleRepo.save(role.setName(name));
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public List<Role> getRoleList() {
        List<Role> roles = roleRepo.findByIsDelete(0);
        return roles;
    }

    @Override
    public List<UserRole> insertUserRole(List<UserRole> userRoles) {
        if (userRoles!=null){
            userRoleRepo.saveAll(userRoles);
            return userRoles;
        }else {
            return null;
        }
    }

    @Override
    public int updateUserRole(UserRole userRole) {
        return 0;
    }

    @Override
    public List<UserRole> getUserRole() {
        return null;
    }

    @Override
    public RolePermission insertRolePm(RolePermission rolePermission) {
        return null;
    }

    @Override
    public int updateRolePm(RolePermission rolePermission) {
        return 0;
    }

    @Override
    public List<RolePermission> getRolePm() {
        return null;
    }

    Role findRole(String roleId){
        return roleRepo.findById(roleId).get();
    }


}