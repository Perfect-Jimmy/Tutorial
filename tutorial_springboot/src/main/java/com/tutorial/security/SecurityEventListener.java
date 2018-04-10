package com.tutorial.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Jimmy. 2018/4/9  14:30
 * 安全相关的事件发布到ApplicationEventPublisher,包括成功,失败和拒绝
 */
@Component
public class SecurityEventListener implements ApplicationListener<AbstractAuthenticationEvent> {
    private  static final Logger LOGGER = LoggerFactory.getLogger(SecurityEventListener.class);

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        LOGGER.info("security event is {}", event.getClass().getCanonicalName());
    }
}
