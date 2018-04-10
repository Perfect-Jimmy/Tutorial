package com.tutorial.security;

import com.tutorial.domain.Role;
import com.tutorial.domain.UserRole;
import com.tutorial.repository.RoleRepository;
import com.tutorial.repository.UserRepository;
import com.tutorial.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy. 2018/4/8  10:14
 * 用户登录处理
 * SPRING SECURITY  UserDetailsService 用户服务
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private static Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("loadUserByUsername --> [{}]", username);
        //查询user
        List<com.tutorial.domain.User> userList = userRepository.findByUserName(username);
        if(CollectionUtils.isEmpty(userList)){
            throw new UsernameNotFoundException("username not found.");
        }
        com.tutorial.domain.User user = userList.get(0);
        //查询user-role关系
        List<UserRole> userRoleList = userRoleRepository.findByUserId(user.getId());
        //查询role
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(UserRole userRole:userRoleList){
            Role role = roleRepository.getOne(userRole.getRoleId());
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));//角色特权;
        }
        return new User(username, user.getPassword(), true, true, true, true, grantedAuthorities);
    }
}
