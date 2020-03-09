package com.boot.dubbo.security.repository;

import com.boot.dubbo.security.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.security.model.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
