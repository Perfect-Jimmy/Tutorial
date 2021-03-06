package com.tutorial.test;

import com.tutorial.SecurityStart;
import com.tutorial.domain.Account;
import com.tutorial.domain.Role;
import com.tutorial.domain.User;
import com.tutorial.domain.UserRole;
import com.tutorial.service.AccountService;
import com.tutorial.service.RoleService;
import com.tutorial.service.UserRoleService;
import com.tutorial.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Jimmy. 2018/3/16  10:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityStart.class)
public class JPATest {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void saveUser(){
        User user = new User();
        user.setId(1L);
        user.setUserName("Jimmy");
        user.setAddress("wuxi");
        user.setBirthDay(new Date());
        user.setInCome(100L);
        user.setPassword(new BCryptPasswordEncoder().encode("123456").trim());
      //  user.setUserDesc("");
        userService.saveOrUpdate(user);
    }

    @Test
    public void saveAccount(){
        Account account = new Account();
        account.setUserName("jimmy");
        account.setPassword("123456");
        accountService.saveAccount(account);
    }

    @Test
    public void queryAccount(){
        Account account = accountService.queryById(1L);
        System.out.println(account);
    }



    @Test
    public void saveRole(){
        Role role = new Role();
        // role.setRoleName("admin");
        role.setRoleName("guest");
        roleService.saveOrUpdate(role);
    }

    @Test
    public void saveUserRole(){
        UserRole userRole = new UserRole();
        userRole.setUserId(1L);
        userRole.setRoleId(1L);
        userRoleService.saveOrUpdate(userRole);
    }
}
