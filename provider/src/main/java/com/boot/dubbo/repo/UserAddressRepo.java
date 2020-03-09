package com.boot.dubbo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.boot.dubbo.model.UserAddress;
import java.util.List;

/**
 * Created by ZhouFangyuan on 2020-03-03.
 * Time: 23:20
 * Information:
 */
@Repository
public interface UserAddressRepo extends CrudRepository<UserAddress, Integer> {

    /**
     * @param userId
     * @return
     */
    List<UserAddress> findByUserId(String userId);
}
