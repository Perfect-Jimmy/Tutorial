package com.tutorial.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

/**
 * Created by Jimmy. 2018/4/8  10:49
 * 自定义验证方式
 * SPRING SECURITY AuthenticationProvider 身份认证服务
 */
//@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails user = customUserDetailsService.loadUserByUsername(username);
        LOGGER.info("password={}, needPassword={}", password, user.getPassword());
        //密码匹配验证
        if (passwordEncoder().matches(password, user.getPassword())) {
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        throw new BadCredentialsException("Wrong password.");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
