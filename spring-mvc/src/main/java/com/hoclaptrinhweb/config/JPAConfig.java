package com.hoclaptrinhweb.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.hoclaptrinhweb.repository"})
@EnableTransactionManagement
public class JPAConfig {
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPersistenceUnitName("persistence-data");//Trung gian giữa các entity classes và các tables dưới database
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}
	
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	//get the connection from database
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/springmvctest");
		dataSource.setUsername("root");
		dataSource.setPassword("hoailinh9966");
		return dataSource;
	}
	
	Properties additionalProperties() {
		Properties properties = new Properties();
		//If the value is CREATE then the hibernate first drops the existing tables data and structure,
		//then creates new tables and executes the operations on the newly created tables.
		//The only problem with the value “create” is, we lose existing table data.
		//properties.setProperty("hibernate.hbm2ddl.auto", "create");
		
		//If the value is update then, Hibernate checks for the table and columns.
		//If a table doesn’t exist then it creates new tables and where as if a column doesn’t exist it creates new columns for it.
		//But in the case of value “update” hibernate doesn’t drop any existing table, so that we don’t lose existing table data.
		//properties.setProperty("hibernate.hbm2ddl.auto", "update");
		
		//If the value is update then nothing happens
		properties.setProperty("hibernate.hbm2ddl.auto", "none");//use when database is stable
		properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");//hỗ trợ tạo bảng trung gian (fetchType.LAZY)
		return properties;
	}
}
