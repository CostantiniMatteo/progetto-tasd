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
@EnableJpaRepositories(basePackages = "com.tasd.seekers.skills.repo", 
						entityManagerFactoryRef = "skillEntityManager",
						transactionManagerRef = "skillTransactionManager")
public class SkillConfiguration {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "skill.datasource")
	public DataSource skillDataSource() {
		return DataSourceBuilder.create().build();
	}

	@PersistenceContext(name = "skills")
	@Bean(name = "skillEntityManager")
	public LocalContainerEntityManagerFactoryBean skillEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(skillDataSource()).packages("com.tasd.seekers.skills.entities")
				.persistenceUnit("skills").build();
	}

	@Bean(name = "skillTransactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(skillEntityManagerFactory(builder).getObject());
		tm.setDataSource(skillDataSource());
		return tm;
	}

}
