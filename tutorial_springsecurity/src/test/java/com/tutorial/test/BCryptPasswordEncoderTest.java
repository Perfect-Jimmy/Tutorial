package com.tutorial.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Jimmy. 2018/4/9  09:32
 */
public class BCryptPasswordEncoderTest {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456").trim());
    }

}
