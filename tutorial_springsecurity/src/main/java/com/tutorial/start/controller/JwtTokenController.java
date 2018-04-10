package com.tutorial.start.controller;

import com.tutorial.domain.Account;
import com.tutorial.service.AccountService;
import com.tutorial.util.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.Date;

@RestController
public class JwtTokenController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenController.class);

    @Autowired
    private AccountService accountService;

    /**
     * 获取token
     * @param appId      编号
     * @param appSecret  密码
     * @return
     */
    @RequestMapping("token")
    public String token(@RequestParam Long appId,@RequestParam String appSecret){
        if(appId == null){
            return "获取失败";
        }
        if(appSecret == null || appSecret.trim() == ""){
            return "获取失败";
        }
        //根据appId查询账号实体
        Account account = accountService.queryById(appId);
        if(account == null){
            return "账号不存在";
        }
        //查询token是否存在
        if(StringUtils.isEmpty(account.getToken())){
            //创建token
            String token = createNewToken(appId);
            account.setToken(token);
            accountService.saveAccount(account);
            LOGGER.info("创建token:"+token);
            return token;
        }else{//判断是否过期
            //判断数据库中token是否过期，如果没有过期不需要更新直接返回数据库中的token即可
            //数据库中生成时间
          /*  long dbBuildTime = Long.valueOf(tokenDBEntity.getBuildTime());
            //当前时间
            long currentTime = System.currentTimeMillis();
            //如果当前时间 - 数据库中生成时间 < 7200 证明可以正常使用
            long second = TimeUnit.MILLISECONDS.toSeconds(currentTime - dbBuildTime);
            if (second > 0 && second < 7200) {
                tokenStr = new String(tokenDBEntity.getToken());
            }*/
        }
            return Constants.FAILURE;
    }


    /**
     * 创建token
     * @param appId
     * @return
     */
    private String createNewToken(Long appId){
        //获取当前时间
        Date now = new Date(System.currentTimeMillis());
        //过期时间
        Date expiration = new Date(now.getTime() + 7200000);//7200000 毫秒
        return Jwts.builder()
                   .setSubject(String.valueOf(appId))//设置主题
                 //.claim(YAuthConstants.Y_AUTH_ROLES, userDbInfo.getRoles())
                   .setIssuedAt(now)
                   .setIssuer("Online YAuth Builder")
                   .setExpiration(expiration)//设置失效时间
                   .signWith(SignatureAlgorithm.HS256, Constants.SIGNING_KEY)//设置算法（必须
                   .compact();//这个是全部设置完成后拼成jwt串的方法
    }


    /**
     * token测试
     * @return
     */
    @RequestMapping("/api/tokenTest")
    public String tokenTest(){
        return "1";
    }
}
