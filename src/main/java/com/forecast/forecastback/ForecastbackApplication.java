package com.forecast.forecastback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class ForecastbackApplication {

  public static void main(String[] args) {
    SpringApplication.run(ForecastbackApplication.class, args);
  }
}
