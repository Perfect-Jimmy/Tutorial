package com.tutorial.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jimmy. 2018/4/8  18:12
 * 这里保存了我们的登录前请求信息,可以不用获取RequestCache就可实现直接从登录页面重定向到登录前访问的URL
 */
@Component
public class CustomerLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler /*SavedRequestAwareAuthenticationSuccessHandler*/ {
    private static Logger LOGGER = LoggerFactory.getLogger(CustomerLoginSuccessHandler.class);
    public final Integer SESSION_TIMEOUT_IN_SECONDS = 60 * 1;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //SecurityContextHolder是Spring Security的核心组件,可获取框架内的一些信息
        //得到登录成功后的UserDetails
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
           // request.getSession().setAttribute("admin", ((CustomerUserDetails) principal).getAdmin());
            LOGGER.info("Method onAuthenticationSuccess "+((UserDetails) principal).getUsername());
        }
        request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
