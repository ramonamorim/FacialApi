package br.com.facial;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@ComponentScan(basePackages = { FacialApplicationConfig.BASE_PACKAGE })
@EnableJpaRepositories(basePackages = { FacialApplicationConfig.BASE_PACKAGE })
public class FacialApplicationConfig {
	
	public static final String BASE_PACKAGE = "br.com.facial";

	
	 @Bean
	    @ConfigurationProperties(prefix = "spring.datasource.facial-ds")
	    public DataSource dataSource() {
	        return DataSourceBuilder.create().build();
	    }
	 
	 @Bean
	    @ConfigurationProperties(prefix = "spring.jpa")
	    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
	        return new HibernateJpaVendorAdapter();
	    }
	 
	 @Bean
	    public EntityManagerFactory entityManagerFactory() {
	        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	        factory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
	        factory.setPackagesToScan(FacialApplicationConfig.BASE_PACKAGE);
	        factory.setDataSource(dataSource());
	        factory.afterPropertiesSet();
	        return factory.getObject();
	    }
	 
	 @Bean
	    public PlatformTransactionManager transactionManager() {
	        JpaTransactionManager txManager = new JpaTransactionManager();
	        txManager.setEntityManagerFactory(entityManagerFactory());
	        return txManager;
	    }
}
