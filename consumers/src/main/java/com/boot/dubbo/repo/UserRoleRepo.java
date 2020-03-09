package com.boot.dubbo.repo;

import com.boot.dubbo.model.Role;
import com.boot.dubbo.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-06.
 * Time: 14:15
 * Information:
 */
public interface UserRoleRepo extends CrudRepository<UserRole,String> {
}
