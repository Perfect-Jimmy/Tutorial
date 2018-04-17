package com.tutorial.configurer;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by Jimmy. 2018/3/29  11:04
 */
@Configuration
public class DataSourceConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfigurer.class);

    @Value("${spring.data.source.url}")
    private String url;
    @Value("${spring.data.source.username}")
    private String userName;
    @Value("${spring.data.source.password}")
    private String password;
    //通用配置
    @Value("${spring.data.source.driver-class-name}")
    private String driverClassName;
    @Value("${data.source.pool.connectionTimeout}")
    private int connectionTimeout;
    @Value("${data.source.pool.initialSize}")
    private int initialSize;
    @Value("${data.source.pool.maxWait}")
    private int maxWait;
    @Value("${data.source.pool.maxActive}")
    private int maxActive;
    @Value("${data.source.pool.minIdle}")
    private int minIdle;
    @Value("${data.source.pool.maxIdle}")
    private int maxIdle;
    @Value("${data.source.pool.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${data.source.pool.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${data.source.pool.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${data.source.pool.testWhileIdle}")
    private Boolean testWhileIdle;
    @Value("${data.source.pool.validationQuery}")
    private String validationQuery;
    @Value("${data.source.pool.removeAbandoned}")
    private Boolean removeAbandoned;
    @Value("${data.source.pool.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;

    /**
     * 主数据源
     * @return
     */
    @Primary
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    public DataSource dataSource() {
        return getDruidDataSource(userName, password, url);
    }


  /*  @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    public DataSource secondaryDataSource() {
        return getDruidDataSource("root", "ysten123","jdbc:mysql://192.168.50.243:3306/test_s??characterEncoding=utf8&zeroDateTimeBehavior=convertToNull");
    }*/

    /**
     * 公共类
     * @param username
     * @param password
     * @param url
     * @return
     */
    private DruidDataSource getDruidDataSource(String username, String password, String url) {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(url.trim());
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
     // datasource.setTestOnReturn(testOnReturn);
     // datasource.setPoolPreparedStatements(poolPreparedStatements);
     // datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
      /*  try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter : {0}", e);
        }
        datasource.setConnectionProperties(connectionProperties);*/

        return datasource;
    }

}
