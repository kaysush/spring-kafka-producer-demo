package com.keda.kedademoproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties
public class KedaDemoProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(KedaDemoProducerApplication.class, args);
  }

}
