package com.boot.dubbo.config.security;

import cn.hutool.crypto.SecureUtil;
import com.boot.dubbo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-05.
 * Time: 00:53
 * Information:
 */
@Component
class SimpleAuthenticationManager implements AuthenticationManager {
    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // 构建一个角色列表
    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // 验证方法
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        //login success
        UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getName());
//         这里我们自定义了验证通过条件：username与password相同就可以通过认证
        if (userDetails.getUsername().equals(auth.getName())
                && userDetails.getPassword().equals(SecureUtil.md5(auth.getCredentials().toString()))) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
            ((UsernamePasswordAuthenticationToken) authentication).setDetails(userDetails);
            return authentication;
        }
//         没有通过认证则抛出密码错误异常
        throw new BadCredentialsException("Bad Credentials");
    }
}
