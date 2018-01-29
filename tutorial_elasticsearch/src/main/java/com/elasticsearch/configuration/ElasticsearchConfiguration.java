package com.elasticsearch.configuration;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jimmy. 2018/1/29  11:08
 */
@Configuration
public class ElasticsearchConfiguration {
    @Value("${spring.elasticsearch.jest.uris}")
    private String uris;

    @Bean
    public JestClient jestClient(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(uris)
                .multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                .defaultMaxTotalConnectionPerRoute(10)
                // and no more 20 connections in total
                .maxTotalConnection(10)
              //.readTimeout()
              //.connTimeout()
                .build());
        JestClient jestClient = factory.getObject();
        System.out.println(jestClient);
        return jestClient;
    }
}
