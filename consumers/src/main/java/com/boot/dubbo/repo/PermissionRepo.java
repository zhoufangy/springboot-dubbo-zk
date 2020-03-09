package com.boot.dubbo.repo;

import com.boot.dubbo.model.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 17:52
 * Information:
 */
@Repository
public interface PermissionRepo extends CrudRepository<Permission, String> {

    @Query(value = "SELECT B.id, B.role_id, B.permission_id,A.name,C.url " +
            "FROM role AS A " +
            "LEFT JOIN role_permission B ON A.id=B.role_id " +
            "LEFT JOIN permission AS C ON B.permission_id=C.id",
            nativeQuery = true)
    List<Object[]> getRolePermissions();

    List<Permission> findByIsDelete(Integer isDelete);
}
