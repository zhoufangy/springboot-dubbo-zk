package com.boot.dubbo.repo;

import com.boot.dubbo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-04.
 * Time: 17:48
 * Information:
 */
@Repository
public interface UserRepo extends CrudRepository<User, String> {

    User findUserByUsername(String username);

    List<User> findByIsDelete(Integer isDelete);
}
