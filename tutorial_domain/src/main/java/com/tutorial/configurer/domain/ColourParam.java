package com.tutorial.configurer.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Jimmy. 2018/3/19  18:13
 */
@Data
@Component
@ConfigurationProperties
public class ColourParam {
    private String name;
}
