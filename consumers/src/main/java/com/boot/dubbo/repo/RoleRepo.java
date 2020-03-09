package com.boot.dubbo.repo;

import com.boot.dubbo.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 17:51
 * Information:
 */
@Repository
public interface RoleRepo extends CrudRepository<Role,String> {

    List<Role> findByIsDelete(Integer isDelete);
    @Query(value = "SELECT r.*" +
            "FROM user_role ur " +
            "inner join role r ON r.id=ur.role_id " +
            "where ur.user_id=:userId",
            nativeQuery = true)
    List<Role> findByUserId(String userId);

}
