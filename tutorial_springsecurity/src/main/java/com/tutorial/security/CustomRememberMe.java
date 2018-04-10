package com.tutorial.security;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

/**
 * Created by Jimmy. 2018/4/8  11:11
 */
public class CustomRememberMe implements PersistentTokenRepository {
    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {

    }

    @Override
    public void updateToken(String s, String s1, Date date) {

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String s) {
        return null;
    }

    @Override
    public void removeUserTokens(String s) {

    }
   /* protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private RememberMeMapper remembermeMapper;
    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        RememberMe rememberMe = new RememberMe();
        rememberMe.setUsername(persistentRememberMeToken.getUsername());
        rememberMe.setSeries(persistentRememberMeToken.getSeries());
        rememberMe.setDate(persistentRememberMeToken.getDate());
        rememberMe.setTokenValue(persistentRememberMeToken.getTokenValue());
        remembermeMapper.insert(rememberMe);
    }
    @Override
    public void updateToken(String s, String s1, Date date) {
        RememberMe rememberMe = new RememberMe();
        rememberMe.setUsername("");
        rememberMe.setSeries(s);
        rememberMe.setTokenValue(s1);
        rememberMe.setDate(date);
        remembermeMapper.updateByPK(rememberMe);
    }
    @Override
    public PersistentRememberMeToken getTokenForSeries(String s) {
        RememberMe rememberMe = remembermeMapper.selectByPK(s);
        PersistentRememberMeToken persistentRememberMeToken =
                new PersistentRememberMeToken(rememberMe.getUsername(),
                        rememberMe.getSeries(),
                        rememberMe.getTokenValue(),
                        rememberMe.getDate()
                );
        return persistentRememberMeToken;
    }
    @Override
    public void removeUserTokens(String s) {
        remembermeMapper.deleteByPK(s);
    }*/
}
