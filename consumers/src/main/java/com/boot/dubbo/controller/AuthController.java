package com.boot.dubbo.controller;

import com.boot.dubbo.model.Permission;
import com.boot.dubbo.model.Role;
import com.boot.dubbo.model.User;
import com.boot.dubbo.model.VO.UserVO;
import com.boot.dubbo.service.AuthService;
import com.boot.dubbo.utils.BaseTool;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO
 * 1. Loombook
 * 2. spring-boot-security @PreAuthorize("hasAuthority('BookList')")
 * 3. using .http
 */
@RestController
@RequestMapping(value = "/api")
public class AuthController {
    @Autowired
    private AuthService authService;


    /**
     * 登录
     */
    @PostMapping(value = "/user/login")
    @ResponseBody
    public Object login(String username, String password) throws AuthenticationException {
        // 登录成功会返回Token给用户
        return BaseTool.buildSuccessResult(authService.login(username, password));
    }

    //TODO
    @PostMapping(value = "/user/logout")
    public Object logout(String username, String password) throws AuthenticationException {
        return null;
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public Object userList() throws AuthenticationException {
        List<UserVO> userVOS = Lists.newArrayList();
        List<User> users = authService.getUserList();
        if (users != null) {
            for (User u : users) {
                UserVO userVO = new UserVO().setId(u.getId()).setUsername(u.getUsername());
                userVOS.add(userVO);
            }
            return BaseTool.buildSuccessResult(userVOS);
        } else {
            return BaseTool.buildFailedResult("获取列表失败");
        }
    }

    @PostMapping(value = "/user/edit")
    public Object edit(String userId, String username, String password, Integer isDelete) throws AuthenticationException {
        int o = authService.updateUser(userId, username, password, isDelete);
        return respUpdate(o);
    }

    @PostMapping(value = "/user/register")
    public Object register(String username, String password) throws AuthenticationException {
        User user = authService.insertUser(username, password);
        if (user != null) {
            return BaseTool.buildSuccessResult("保存成功");
        } else {
            return BaseTool.buildFailedResult("保存失败");
        }
    }

    @RequestMapping(value = "/permission/list", method = RequestMethod.GET)
    public Object pmList() throws AuthenticationException {
        List<Permission> pmList = authService.getPmList();
        if (pmList != null) {
            return BaseTool.buildSuccessResult(pmList);
        } else {
            return BaseTool.buildFailedResult("获取列表失败");
        }
    }

    @PostMapping(value = "/permission/add")
    public Object permissionAdd(String url, String description, String name, Integer pid) throws AuthenticationException {
        Permission permission = authService.insertPm(url, description, name, pid);
        if (permission != null) {
            return BaseTool.buildSuccessResult("保存成功");
        } else {
            return BaseTool.buildFailedResult("保存失败");
        }
    }

    /**
     * 更新或逻辑删除
     *
     * @param pmId
     * @param url
     * @param description
     * @param name
     * @param pid
     * @param isDelete
     * @return
     * @throws AuthenticationException
     */
    @PostMapping(value = "/permission/edit")
    public Object permissionEdit(String pmId, String url, String description, String name, Integer pid, Integer isDelete) throws AuthenticationException {
        int o = authService.updatePm(pmId, url, description, name, pid, isDelete);
        return respUpdate(o);
    }

    private Object respUpdate(int o) {
        if (o == 1) {
            return BaseTool.buildSuccessResult("更新成功");
        } else {
            return BaseTool.buildFailedResult("更新失败");
        }
    }

    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    public Object roleList() throws AuthenticationException {
        List<Role> roles = authService.getRoleList();
        if (roles != null) {
            return BaseTool.buildSuccessResult(roles);
        } else {
            return BaseTool.buildFailedResult("获取列表失败");
        }
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @ResponseBody
    public String ping() {
        return "ok";
    }

}
