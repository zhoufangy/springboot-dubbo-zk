package com.boot.dubbo.config.security.deprecate;

import com.boot.dubbo.model.RolePermission;
import com.boot.dubbo.repo.PermissionRepo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 18:31
 * Information:
 */

//@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionRepo permissionRepo;

    /**
     * 每一个资源所需要的角色 Collection<ConfigAttribute>决策器会用到
     */
    private static HashMap<String, Collection<ConfigAttribute>> map =null;


    /**
     * 返回请求的资源需要的角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
            String url = it.next();
            if (new AntPathRequestMatcher( url ).matches( request )) {
                return map.get( url );
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        //初始化 所有资源 对应的角色
        loadResourceDefine();
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * 初始化 所有资源 对应的角色
     */
    public void loadResourceDefine() {
        map = new HashMap<>(16);
        //权限资源 和 角色对应的表  也就是 角色权限 中间表
        List<RolePermission> rolePermissions = Lists.newArrayList();
        List<Object[]> rolePermO= permissionRepo.getRolePermissions();
        if (rolePermO != null && rolePermO.size() > 0) {
            for (Object[] o : rolePermO) {
                if (o != null && o.length > 0) {
                    String url = String.valueOf(o[4]);
                    String roleName = String.valueOf(o[3]);
                    ConfigAttribute role = new SecurityConfig(roleName);

                    if (map.containsKey(url)) {
                        map.get(url).add(role);
                    } else {
                        List<ConfigAttribute> list = new ArrayList<>();
                        list.add(role);
                        map.put(url, list);
                    }
                }
            }
        }
        //某个资源 可以被哪些角色访问
        /*for (RolePermisson rolePermisson : rolePermissons) {

            String url = rolePermisson.getUrl();
            String roleName = rolePermisson.getRoleName();
            ConfigAttribute role = new SecurityConfig(roleName);

            if(map.containsKey(url)){
                map.get(url).add(role);
            }else{
                List<ConfigAttribute> list =  new ArrayList<>();
                list.add( role );
                map.put( url , list );
            }
        }*/
    }


}