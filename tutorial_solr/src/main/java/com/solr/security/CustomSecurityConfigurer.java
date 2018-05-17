package com.solr.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Jimmy. 2018/4/4  14:17
 * https://github.com/Mr-Pro/membership
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true/*, securedEnabled = true*//*, jsr250Enabled = true*/) // 开启security注解
public class CustomSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(CustomSecurityConfigurer.class);
    private static final String KEY = "security";



    /**
     * 配置该项目与用户的关联,也称作认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       /* auth
                .userDetailsService(userDetailsService)//注册自己定制的UserDetailsService
                .passwordEncoder(new BCryptPasswordEncoder()); // 配置密码加密器*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //获取请求方面的验证器
                .antMatchers("/**").permitAll()// 访问当前配置的路径可通过认证
                //访问其他路径需要认证和角色权限
                //.anyRequest().hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable(); //关闭csrf,默认开启
    }

}
