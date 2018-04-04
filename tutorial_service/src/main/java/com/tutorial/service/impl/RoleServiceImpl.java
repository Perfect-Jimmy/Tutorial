package com.tutorial.service.impl;

import com.tutorial.domain.Role;
import com.tutorial.repository.RoleRepository;
import com.tutorial.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jimmy. 2018/4/4  10:26
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role saveOrUpdate(Role role) {
        return roleRepository.save(role);
    }
}
