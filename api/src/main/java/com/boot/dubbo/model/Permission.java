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
 * Time: 17:47
 * Information:
 */

@Entity
@Table(name = "permission")
public class Permission implements Serializable {
    @Id
    private String id;
    private String url;
    private String name;
    private String description;
    private Integer pid;
    @Column(name = "is_delete")
    private Integer isDelete;

    public String getId() {
        return id;
    }

    public Permission setId(String id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Permission setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public Permission setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Permission setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public Permission setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public Permission setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }
}
