package com.keda.kedademoproducer;

import java.util.Map;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfiguration {


  @Value("${spring.kafka.properties.bootstrap.servers}")
  private String bootstrapServers;

  @Value("${spring.kafka.properties.security.protocol}")
  private String securityProtocol;

  @Value("${spring.kafka.properties.sasl.mechanism}")
  private String saslMechanism;

  @Value("${spring.kafka.properties.sasl.jaas.config}")
  private String jaasConfig;

  @Bean
  public Map<String, Object> producerConfigs() {
    return Map.of(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
        CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocol,
        SaslConfigs.SASL_MECHANISM, saslMechanism,
        SaslConfigs.SASL_JAAS_CONFIG, jaasConfig,
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class
    );
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }


}
