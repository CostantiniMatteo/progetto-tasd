package com.tasd.seekers;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages="com.tasd.seekers.repo", 
						entityManagerFactoryRef="entityManager", 
						transactionManagerRef="seekerTransactionManager")
public class SeekerConfiguration {
	
	@Bean(name ="dataSource")
	@Primary
	@ConfigurationProperties(prefix="seeker.datasource")
	public DataSource seekerDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "entityManager")
	@Primary
	@PersistenceContext(name = "seekers")
	public LocalContainerEntityManagerFactoryBean seekerEntityManagerFactory(
	        EntityManagerFactoryBuilder builder) {
	    return builder
	            .dataSource(seekerDataSource())
	            .packages("com.tasd.seekers.entities")
	            .persistenceUnit("seekers")
	            .build();
	}
	
	@Bean(name = "seekerTransactionManager")
	@Primary
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(seekerEntityManagerFactory(builder).getObject());
		tm.setDataSource(seekerDataSource());
		return tm;
	}

}
