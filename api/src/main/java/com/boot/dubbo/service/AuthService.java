package com.boot.dubbo.service;

import com.boot.dubbo.model.*;
import com.boot.dubbo.model.VO.UserVO;

import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 17:44
 * Information:
 */
public interface AuthService {

    String login(String username, String password);

    User insertUser(String username, String password);

    int updateUser(String userId, String username, String password,Integer isDelete);

    List<User> getUserList();

    Permission insertPm(String url, String description, String name, Integer pid);

    int updatePm(String pmId,String url, String description, String name, Integer pid,Integer isDelete);

    List<Permission> getPmList();

    Role insertRole(String name);

    int updateRole(String roleId,String name,Integer isDelete);

    List<Role> getRoleList();

    List<UserRole> insertUserRole(List<UserRole> userRole);

    int updateUserRole(UserRole userRole);

    List<UserRole> getUserRole();

    RolePermission insertRolePm(RolePermission rolePermission);

    int updateRolePm(RolePermission rolePermission);

    List<RolePermission> getRolePm();
}