package com.vincentmegia;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.vincentmegia")
public class HibernateConfiguration {

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        var localeSessionFactoryBean = new LocalSessionFactoryBean();
        localeSessionFactoryBean.setDataSource(getDataSource());
        localeSessionFactoryBean.setPackagesToScan(new String[] {"com.vincentmegia.models" });
        localeSessionFactoryBean.setHibernateProperties(getHibernateProperties());
        return localeSessionFactoryBean;
    }

    @Bean
    public DataSource getDataSource() {
        var basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.h2.Driver");
        basicDataSource.setUrl("jdbc:h2:mem:family");
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword("");
        return basicDataSource;
    }

    @Bean
    public Properties getHibernateProperties() {
        var properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("show_sql", "true");
        properties.setProperty("spring.h2.console.enabled", "true");
        return properties;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        var transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
