package com.tutorial.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by Jimmy. 2018/4/4  14:17
 * https://github.com/Mr-Pro/membership
 */
//@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true/*, securedEnabled = true*//*, jsr250Enabled = true*/) // 开启security注解
public class CustomSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSecurityConfigurer.class);
    private static final String KEY = "security";

    @Resource
    private CustomUserDetailsService userDetailsService;
    @Resource
    private CustomerLoginSuccessHandler loginSuccessHandler;

    @Autowired
    private DataSource primaryDataSource;

    /**
     * 配置该项目与用户的关联,也称作认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)//注册自己定制的UserDetailsService
                .passwordEncoder(new BCryptPasswordEncoder()); // 配置密码加密器
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //获取请求方面的验证器
                .antMatchers("/", "/error").permitAll()// 访问当前配置的路径可通过认证
                //访问其他路径需要认证和角色权限
                //.anyRequest().hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin() //获取登录认证验证器
                .loginPage("/login") //注册自定义的登录页面URL
         //     .usernameParameter("username").passwordParameter("password")
                .failureForwardUrl("/login") //登录失败后以登录时的请求转发到该链接
                .defaultSuccessUrl("/home")
         //     .successHandler(loginSuccessHandler) //登录成功后调用该处理器
                .permitAll() //登录请求给予通过认证
                .and()
                .logout() //退出登录
                .deleteCookies("JSESSIONID")//清除cookie
                .logoutSuccessUrl("/login") //退出后访问URL
            //    .and()
            //    .sessionManagement().invalidSessionUrl("/sessionInvalid")//session失效后跳转
                .and()// 启用 remember me
                .rememberMe().rememberMeParameter("remember-me")
                .tokenRepository(persistentTokenRepository())//令牌将被存储
                .tokenValiditySeconds(100)//令牌有效时间,单位s
                .rememberMeCookieName("test-s")//cookies的名字,登陆后可以通过浏览器查看cookies名字
               // .key(KEY)
                .and()
                .csrf().disable(); //关闭csrf,默认开启
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(primaryDataSource);
        return tokenRepositoryImpl;
    }


/*.antMatchers("/admin/**","/newuser").access("hasRole('ADMIN')")
	  	.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")*/


   /* private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    public CustomSecurityConfigurer(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;   // 注入自定义 UserDetailsService
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();       // 使用自定义 UserDetailsService
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/login").permitAll()  // 允许所有人访问 "/" 和 "/homePage" 两个路径
                .anyRequest().authenticated()          // 任何请求都需要被认证
                .and()
                .formLogin()
                .loginPage("/login").permitAll()  //  用户登录页面，允许所有人访问，未登录者访问禁止页面时自动跳转到该页面
                .loginProcessingUrl("/login")        // 用户登录提交用户名密码表单时 POST 的路径
                .defaultSuccessUrl("/welcome")    // 登录成功自动跳转的路径
                .and()
                .logout()
                .logoutUrl("/logout")              // 已登录用户登出操作 POST 的路径
                .logoutSuccessUrl("/loginPage")     // 登出成功后自动跳转的路径
                .invalidateHttpSession(true)
        ;
    }*/

  /*  @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许所有用户访问"/"和"/home"
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页是"/login"
                .loginPage("/login")
                //登录成功后默认跳转到
                .defaultSuccessUrl("/welcome")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                //退出登录后的默认url是"/login"
                .logoutSuccessUrl("/login")
                .permitAll();
        //解决非thymeleaf的form表单提交被拦截问题
        http.csrf().disable();

        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(systemUserService()).passwordEncoder(passwordEncoder());
        //也可以将用户名密码写在内存，不推荐
        auth.inMemoryAuthentication().withUser("admin").password("111111").roles("USER");
    }


    *//** * 设置用户密码的加密方式为MD5加密 *//*
    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    *//** *从数据库中读取用户信息 *//*
    @Bean
    public UserDetailsService systemUserService() {
        return new CustomUserDetailsService();
    }

*/
    /* *//**
     * 自定义用户
     * @param auth
     * @throws Exception
     *//*

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");
    }


    *//**
     * 配置资源权限
     * @param http
     * @throws Exception
     *//*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      *//*  http
                .authorizeRequests()
                .antMatchers("/home/**", "/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();*//*

        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()//其他地址的访问均需验证权限
                .and()
                .formLogin()//当需要用户登录时候转到的登录页面
                .loginPage("/login")//没有授权前,任何访问需要授权才能访问的页面都会先跳转到/login登录页面
                .defaultSuccessUrl("/welcome")//成功登录后跳转的页面
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
    }*/
}
