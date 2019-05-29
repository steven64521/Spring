package com.jiexun.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据源的配置--注解配置 {@link com.alibaba.druid.pool.DruidDataSource}
 */
@Configuration
@PropertySource(value = "classpath:jdbc.properties")
public class DataSourceConfig {
    @Value("${jdbc.datasource.driverClass}")
    private String driverClass;
    @Value("${jdbc.datasource.url}")
    private String url;
    @Value("${jdbc.datasource.user}")
    private String userName;
    @Value("${jdbc.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setMinIdle(10);
        dataSource.setMaxActive(300);
        dataSource.setInitialSize(10);
        dataSource.setMaxWait(1800);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("select 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setFilters("stat, wall");
        return dataSource;
    }
}
