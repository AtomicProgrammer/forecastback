package com.forecast.forecastback.dbConfig.utils;

import io.ebean.config.CurrentUserProvider;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserProviderImpl implements CurrentUserProvider {

  @Override
  public Object currentUser() {
    return 3643635365435L;
  }
}
