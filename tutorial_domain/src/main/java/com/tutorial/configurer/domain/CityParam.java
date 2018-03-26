package com.tutorial.configurer.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Jimmy. 2018/3/26  10:49
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.city")
@PropertySource("classpath:/data-binder.properties")
public class CityParam {
    private Integer id;
    private String name;
}
