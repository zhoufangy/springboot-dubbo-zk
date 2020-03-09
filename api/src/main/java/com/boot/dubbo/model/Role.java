package com.boot.dubbo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 17:46
 * Information:
 */

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    private String id;
    private String name;
    @Column(name = "is_delete")
    private Integer isDelete;

    public String getId() {
        return id;
    }

    public Role setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public Role setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
