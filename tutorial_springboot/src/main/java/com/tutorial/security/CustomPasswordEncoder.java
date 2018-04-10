package com.tutorial.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Jimmy. 2018/4/4  18:14
 * 自定义编码器
 */
//@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence arg0) {
        return arg0.toString();
    }

    @Override
    public boolean matches(CharSequence arg0, String arg1) {
        return arg1.equals(arg0.toString());
    }
}
