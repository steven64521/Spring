package com.jiexun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Spring整合JPA--注解配置
 */
@Configuration
@EnableTransactionManagement
@Import(value = DataSourceConfig.class)
@ComponentScan(value = {"com.jiexun.dao"})
public class SpringJPAConfig {

    /**
     * JPA提供商的适配器，{@link HibernateJpaVendorAdapter}
     * @return
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
        return adapter;
    }

    /**
     * 实体管理工厂,
     * 1、{@link org.springframework.orm.jpa.LocalEntityManagerFactoryBean} 适用于那些仅使用JPA进行数据访问的项目，该FactoryBean将根据JPAPersistenceProvider自动检测配置文件进行工作，一般从"META-INF/persistence.xml"读取配置信息，这种方式最简单，但不能设置Spring中定义的DataSource,且不支持Spring管理的全局事务
     * 2、从JNDI中获取：用户从JavaEE服务器获取指定的EntityManagerFactory,这种方式在进行Spring事务管理时一般要使用JTA事务管理
     * 3、{@link LocalContainerEntityManagerFactoryBean}适用于所有环境的FactoryBean,能全面控制EntityManagerFactory配置，如指定Spring定义的DataSource
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter());
        emfb.setPackagesToScan("com.jiexun.entity");
        return emfb;
    }

    /**
     * 配置Jpa事务管理器
     * @param dataSource
     * @return
     */
    @Bean
    public JpaTransactionManager jpaTransactionManager(DataSource dataSource) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());
        return jpaTransactionManager;
    }
}
