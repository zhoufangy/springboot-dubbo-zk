package com.boot.dubbo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 18:48
 * Information:
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    @Id
    private String id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "is_delete")
    private Integer isDelete;

    public String getId() {
        return id;
    }

    public UserRole setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserRole setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public UserRole setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public UserRole setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }
}
