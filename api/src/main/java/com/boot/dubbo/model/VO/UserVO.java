package com.boot.dubbo.model.VO;

import com.boot.dubbo.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 17:46
 * Information:
 */

public class UserVO{
    private String id;
    private String username;
    private List<Role> authorities;

    public String getId() {
        return id;
    }

    public UserVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserVO setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<Role> getAuthorities() {
        return authorities;
    }

    public UserVO setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
        return this;
    }
}
