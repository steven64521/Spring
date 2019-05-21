package com.jiexun.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = "jdbc.properties")
@EnableTransactionManagement
@ComponentScan(value = "com.jiexun")
@MapperScan(value = "com.jiexun.mapper")
public class MybatisPlusConfig {
    private static final Logger logger = LoggerFactory.getLogger(MybatisPlusConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(env.getProperty("jdbc.datasource.user"));
        dataSource.setDriverClassName(env.getProperty("jdbc.datasource.driverClass"));
        dataSource.setUrl(env.getProperty("jdbc.datasource.url"));
        dataSource.setPassword(env.getProperty("jdbc.datasource.password"));
        return dataSource;
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setTableUnderline(true);
        globalConfig.setDbConfig(dbConfig);
        return globalConfig;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.jiexun.entity");
        sqlSessionFactoryBean.setGlobalConfig(globalConfig());
        return sqlSessionFactoryBean.getObject();
    }



    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}
