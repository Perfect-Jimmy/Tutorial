package com.tutorial.repository;

import com.tutorial.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jimmy. 2018/4/4  10:24
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>,JpaSpecificationExecutor<UserRole> {
    /**
     * 根据userId查询user-role关系
     * @param userId
     * @return
     */
    List<UserRole> findByUserId(Long userId);

}
