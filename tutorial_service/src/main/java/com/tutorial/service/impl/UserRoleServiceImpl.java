package com.tutorial.service.impl;

import com.tutorial.domain.UserRole;
import com.tutorial.repository.UserRoleRepository;
import com.tutorial.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jimmy. 2018/4/4  10:28
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRole saveOrUpdate(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }
}

