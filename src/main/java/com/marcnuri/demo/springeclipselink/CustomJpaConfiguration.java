/*
 * Copyright 2020 Marc Nuri San Felix
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created on 2020-06-27, 7:45
 */
package com.marcnuri.demo.springeclipselink;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.eclipse.persistence.config.BatchWriting;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.logging.SessionLog;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.marcnuri.demo.springeclipselink.repository")
public class CustomJpaConfiguration extends JpaBaseConfiguration {

  protected CustomJpaConfiguration(DataSource dataSource, JpaProperties properties,
    ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
    super(dataSource, properties, jtaTransactionManager);
  }

  @Override
  protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
    return new EclipseLinkJpaVendorAdapter();
  }

  @Override
  protected Map<String, Object> getVendorProperties() {
    final Map<String, Object> ret = new HashMap<>();
    ret.put(PersistenceUnitProperties.BATCH_WRITING, BatchWriting.JDBC);
    return ret;
  }

  @Bean("entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactory(
    EntityManagerFactoryBuilder builder, DataSource dataSource) {

    return builder
      .dataSource(dataSource)
      .packages("com.marcnuri.demo.springeclipselink.repository")
      .persistenceUnit("YourPersistenceUnitName")
      .properties(initJpaProperties()).build();
  }

  @Bean
  public static DataSource dataSource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.h2.Driver");
    dataSource.setUrl("jdbc:h2:mem:marcnuridemo;DB_CLOSE_DELAY=-1");
    dataSource.setUsername("sa");
    dataSource.setPassword("password");
    return dataSource;
  }

  @Bean
  public static PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    final JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(emf);
    return transactionManager;
  }

  @Bean
  @Primary
  public static JpaProperties properties() {
    final JpaProperties jpaProperties = new JpaProperties();
    jpaProperties.setShowSql(true);
    jpaProperties.setDatabasePlatform("org.eclipse.persistence.platform.database.H2Platform");
    return jpaProperties;
  }

  private static Map<String, ?> initJpaProperties() {
    final Map<String, Object> ret = new HashMap<>();
    // Add any JpaProperty you are interested in and is supported by your Database and JPA implementation
    ret.put(PersistenceUnitProperties.BATCH_WRITING, BatchWriting.JDBC);
    ret.put(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINEST_LABEL);
    ret.put(PersistenceUnitProperties.WEAVING, "false");
    ret.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_ONLY);
    ret.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_DATABASE_GENERATION);
    return ret;
  }
}
