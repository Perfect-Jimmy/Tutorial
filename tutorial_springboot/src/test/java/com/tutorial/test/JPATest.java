package com.tutorial.test;

import com.tutorial.AppStart;
import com.tutorial.domain.User;
import com.tutorial.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Jimmy. 2018/3/16  10:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppStart.class)
public class JPATest {
    @Autowired
    private UserService userService;

    @Test
    public void saveUser(){
        User user = new User();
        user.setId(1L);
        user.setUserName("Jimmy");
        user.setAddress("wuxi");
        user.setBirthDay(new Date());
        user.setInCome(100L);
        user.setPassword("123456");
      //  user.setUserDesc("");
        userService.saveOrUpdate(user);
    }
}
