package com.jiexun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ${@link NamedParameterJdbcTemplate} 的使用
 */
@Configuration
@PropertySource("jdbc.properties")
@ComponentScan(value = "com.jiexun")
@EnableTransactionManagement
public class NamedParameterJdbcConfig {
    @Value("${jdbc.datasource.driverClass}")
    private String driverClass;
    @Value("${jdbc.datasource.url}")
    private String jdbcUrl;
    @Value("${jdbc.datasource.user}")
    private String userName;
    @Value("${jdbc.datasource.password}")
    private String password;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DriverManagerDataSource dataSource) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        return namedParameterJdbcTemplate;
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert(DriverManagerDataSource dataSource) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
        return simpleJdbcInsert;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DriverManagerDataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }
}
