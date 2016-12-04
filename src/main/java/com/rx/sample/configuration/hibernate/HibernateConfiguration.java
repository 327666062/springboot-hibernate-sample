package com.rx.sample.configuration.hibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class HibernateConfiguration {

	@Bean
	public DataSource dataSource(@Value("${dataSource.maxActive}") Integer maxActive,
			@Value("${dataSource.maxIdle}") Integer maxIdle, @Value("${dataSource.maxWait}") Integer maxWait,
			@Value("${dataSource.driverClassName}") String driverClassName, @Value("${dataSource.url}") String url,
			@Value("${dataSource.username}") String username, @Value("${dataSource.password}") String password,
			@Value("${dataSource.validationQuery}") String validationQuery) {

		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setMaxActive(maxActive);
		dataSource.setMaxIdle(maxIdle);
		dataSource.setMaxWait(maxWait);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setValidationQuery(validationQuery);

		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(@Autowired DataSource dataSource,
			@Value("${hibernate.scanPackages}") String scanPackages, @Value("${hibernate.dialect}") String dialect,
			@Value("${hibernate.show_sql}") String showSql, @Value("${hibernate.format_sql}") String formatSql,
			@Value("${hibernate.use_sql_comments}") String useSqlComments,
			@Value("${hibernate.hbm2ddl.auto}") String hbm2ddlAuto,
			@Value("${hibernate.cache.use_query_cache}") String useQueryCache,
			@Value("${hibernate.cache.use_second_level_cache}") String useSecondLevelCache) {

		Properties hibernateProperties = new Properties();

		hibernateProperties.put("hibernate.dialect", dialect);
		hibernateProperties.put("hibernate.cache.use_second_level_cache", useSecondLevelCache);
		hibernateProperties.put("hibernate.show_sql", showSql);
		hibernateProperties.put("hibernate.format_sql", formatSql);
		hibernateProperties.put("hibernate.use_sql_comments", useSqlComments);
		hibernateProperties.put("hibernate.cache.use_query_cache", useQueryCache);
		hibernateProperties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);

		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setPackagesToScan(scanPackages);
		sessionFactoryBean.setHibernateProperties(hibernateProperties);

		return sessionFactoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager(@Autowired SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

}
