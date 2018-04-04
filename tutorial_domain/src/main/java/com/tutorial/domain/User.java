package com.tutorial.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Jimmy. 2018/1/30  16:46
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable,UserDetails {

    private static final long serialVersionUID = 7147781130354030521L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(name="password",nullable = false,length = 18)
    private String password;

    /*编程语言中字符串一般都用String表示，但是数据库中varcahr数值类型有长度限制，一旦需要大文本，则需要text数值类型*/
    @Column(columnDefinition="text")
    private String userDesc;

    /*不映射成列*/
    @Transient
    private Long inCome;

    /*不加注解也会映射成列*/
    private String address;

    /**
     * @DatetimeFormat是将String转换成Date，一般前台给后台传值时用
     * @JsonFormat(pattern="yyyy-MM-dd")  将Date转换成String  一般后台传值给前台时
     */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date birthDay;

    @ManyToMany
    @JoinTable(name="user_role",joinColumns = {@JoinColumn(name="userId")},inverseJoinColumns = {@JoinColumn(name="roleId")})
    private List<Role> roles;

    //security
    //返回分配给用户的角色列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       /* List<GrantedAuthority> auths = new ArrayList<>();
        List<Role> roles = getRoles();
        for(Role role:roles){
            auths.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return auths;*/

        List<SimpleGrantedAuthority> simpleAuthorities = new ArrayList<>();
        for(GrantedAuthority authority : this.roles){
            simpleAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return simpleAuthorities;
    }

    //返回帐号
    @Override
    public String getUsername() {
        return null;
    }

    //账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //密码是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账户是否激活
    @Override
    public boolean isEnabled() {
        return true;
    }



}
