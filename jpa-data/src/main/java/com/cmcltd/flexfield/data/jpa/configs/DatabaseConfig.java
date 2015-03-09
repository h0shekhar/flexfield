package com.cmcltd.flexfield.data.jpa.configs;

import java.util.Properties;

import javax.sql.DataSource;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains database configurations.
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class DatabaseConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

  // ==============
  // PRIVATE FIELDS
  // ==============
  
  @Autowired
  private Environment _env;

  @Autowired
  private DataSource _dataSource;

  @Autowired
  private LocalContainerEntityManagerFactoryBean _entityManagerFactory;

  // ==============
  // PUBLIC METHODS
  // ==============

  /**
   * DataSource definition for database connection. Settings are read from
   * the application.properties file (using the _env object).
   */
  @Bean
  public DataSource dataSource() {
	  logger.info("Inside DataSource");
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(_env.getProperty("db.driver"));
    dataSource.setUrl(_env.getProperty("db.url"));
    dataSource.setUsername(_env.getProperty("db.username"));
    dataSource.setPassword(_env.getProperty("db.password"));
    logger.info("Outside DataSource");
    return dataSource;
  }

  /**
   * Declare the JPA entity manager factory.
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	  logger.info("Starting Entity ManagerFactor instantiated");
	  LocalContainerEntityManagerFactoryBean entityManagerFactory =
        new LocalContainerEntityManagerFactoryBean();
    logger.info("Entity ManagerFactor instantiated");
    entityManagerFactory.setDataSource(_dataSource);
    logger.info("Data Source set");
    // Classpath scanning of @Component, @Service, etc annotated class
    entityManagerFactory.setPackagesToScan(
        _env.getProperty("entitymanager.packagesToScan"));
    logger.info("Packagescan instantiated");
    
    // Vendor adapter
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
    logger.info("Vendor manager Adaptor instantiated");
    // Hibernate properties
    Properties additionalProperties = new Properties();
    logger.info("Start Hibernate Property instantiated");
    additionalProperties.put(
        "hibernate.dialect", 
        _env.getProperty("hibernate.dialect"));
    logger.info("hibernate.dialect instantiated");
    additionalProperties.put(
        "hibernate.show_sql", 
        _env.getProperty("hibernate.show_sql"));
    logger.info("hibernate.show_sql instantiated");
//    additionalProperties.put(
//        "hibernate.hbm2ddl.auto", 
//        _env.getProperty("hibernate.hbm2ddl.auto"));
    logger.info("JPA property setup start");
    entityManagerFactory.setJpaProperties(additionalProperties);
    logger.info("JPA property setup");
    
    return entityManagerFactory;
  }

  /**
   * Declare the transaction manager.
   */
  @Bean
  public JpaTransactionManager transactionManager() {
	  logger.info("Start JPA Transaction Manager instantiated");
	  JpaTransactionManager transactionManager = 
        new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
        _entityManagerFactory.getObject());
    return transactionManager;
  }
  
  /**
   * PersistenceExceptionTranslationPostProcessor is a bean post processor
   * which adds an advisor to any bean annotated with Repository so that any
   * platform-specific exceptions are caught and then rethrown as one
   * Spring's unchecked data access exceptions (i.e. a subclass of 
   * DataAccessException).
   */
  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

} // class DatabaseConfig
