package com.forecast.forecastback.dbConfig;


import com.forecast.forecastback.dbConfig.utils.ModUUIDGenerator;
import io.ebean.config.CurrentUserProvider;
import io.ebean.config.ServerConfig;
import javax.sql.DataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AutoConfig implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Bean
  public ServerConfig serverConfig(@Autowired(required = false) DataSource dataSource,
      @Autowired CurrentUserProvider currentUser) {

    ServerConfig config = new ServerConfig();

    config.setName("db");
    config.setCurrentUserProvider(currentUser);

//    // set the spring's datasource and transaction manager.
    config.setDataSource(dataSource);

    config.loadFromProperties();
    config.add(new ModUUIDGenerator());
    // set as default and register so that Model can be
    // used if desired for save() and update() etc
    config.setDefaultServer(true);
    config.setRegister(true);

    return config;
  }


  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
