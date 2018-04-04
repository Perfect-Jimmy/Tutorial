package com.tutorial.configurer;

import com.tutorial.deniedhandler.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Created by Jimmy. 2018/4/4  14:17
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) // 开启security注解
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

   /* @Autowired
    private UserService userService;*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/about").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .passwordEncoder(new CustomPasswordEncoder())//在此处应用自定义PasswordEncoder
                .withUser("admin")
                .password("password")
                .roles("ADMIN")
                .and()
                .withUser("user").password("password").roles("USER");
    }
















    /*  // Spring会自动寻找实现接口的类注入,会找到我们的 UserDetailsServiceImpl  类
    @Bean
    UserDetailsService userDetails(){
        return new UserServiceImpl();
    };

    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(this.userDetails())
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }
*/
    //允许跨域
   /* @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                        .allowCredentials(false).maxAge(3600);
            }
        };
    }*/


    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root")
                .password("root")
                .roles("USER");
    }*/








    /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }*/






    /*//自定义认证实体注入
    @Bean
    UserServiceImpl userServiceImpl(){
        return new UserServiceImpl();
    }

    *//**
     * 自定义验证用户名密码
     * @return
     *//*
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userServiceImpl());
      //  authenticationProvider.setPasswordEncoder(passwordEncoder); // 设置密码加密方式
        return authenticationProvider;
    }

    *//**
     * security
     * 自定义配置
     * @param httpSecurity
     * @throws Exception
     *//*
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .anyRequest().authenticated()//所有请求必须登录后才能访问
                .and().formLogin().loginPage("/login").failureUrl("/error").permitAll()//登录页面,错误页面可直接访问
                .and().logout().permitAll();//注销请求可直接访问
      *//*  httpSecurity.authorizeRequests().antMatchers("/","/css/**", "/js/**", "/fonts/**","/users").permitAll() // 都可以访问
              //  .antMatchers("/h2-console/**").permitAll() // 都可以访问
              //  .antMatchers("/**").hasRole("ADMIN") // 需要相应的角色才能访问
              //  .antMatchers("/console/**").hasAnyRole("ADMIN","USER") // 需要相应的角色才能访问
                .anyRequest().authenticated()//所有请求必须登录后才能访问
                .and()
                .formLogin()   //基于 Form 表单登录验证
                .loginPage("/login").failureUrl("/login?error=true") // 自定义登录界面
               // .and().rememberMe().key(KEY) // 启用 remember me
                .and().exceptionHandling().accessDeniedPage("/403");  // 处理异常,拒绝访问就重定向到 403 页面
        httpSecurity.csrf().ignoringAntMatchers("/h2-console/**"); // 禁用 H2 控制台的 CSRF 防护
        httpSecurity.headers().frameOptions().sameOrigin(); // 允许来自同一来源的H2 控制台的请求*//*
    }

    *//**
     * 认证信息管理
     *
     * @param auth
     * @throws Exception
     *//*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService);
        auth.userDetailsService(userServiceImpl());
        auth.authenticationProvider(authenticationProvider());
    }*/
}
