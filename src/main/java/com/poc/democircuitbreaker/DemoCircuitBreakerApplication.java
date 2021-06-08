package com.poc.democircuitbreaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemoCircuitBreakerApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoCircuitBreakerApplication.class, args);
  }

}
