package com.forecast.forecastback.dbConfig;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EbeanFactoryBean implements FactoryBean<EbeanServer> {

  @Autowired
  ServerConfig config;

  @Override
  public EbeanServer getObject() {
    EbeanServer ebeanServer = EbeanServerFactory.create(config);
    return ebeanServer;
  }

  @Override
  public Class<?> getObjectType() {
    return EbeanServer.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

}
