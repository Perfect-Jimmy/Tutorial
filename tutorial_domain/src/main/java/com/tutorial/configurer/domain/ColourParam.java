package com.tutorial.configurer.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Jimmy. 2018/3/19  18:13
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.colour")
@PropertySource("classpath:/data-binder.properties")
public class ColourParam {
    //此处的字段名必须和配置文件中一致,否则获取不到
    private String name;
}
