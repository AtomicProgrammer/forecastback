package com.forecast.forecastback.dbConfig.utils;

import io.ebean.config.IdGenerator;

public class ModUUIDGenerator implements IdGenerator {


  @Override
  public Object nextValue() {
    return IDGenerator.generate();
  }

  @Override
  public String getName() {
    return "uuid";
  }
}
