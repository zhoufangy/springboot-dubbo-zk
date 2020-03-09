package com.boot.dubbo.model;

import javax.persistence.*;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 17:53
 * Information:
 */

@Entity
@Table(name = "role_permission")
public class RolePermission {
    @Id
    private String id;
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "permission_id")
    private String permissionId;
    @Column(name = "is_delete")
    private Integer isDelete;
    @Transient
    private String roleName;
    @Transient
    private String url;

    public String getId() {
        return id;
    }

    public RolePermission setId(String id) {
        this.id = id;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public RolePermission setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public RolePermission setPermissionId(String permissionId) {
        this.permissionId = permissionId;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public RolePermission setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RolePermission setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public RolePermission setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }
}
