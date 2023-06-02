package com.keda.kedademoproducer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class KedaProducer implements Runnable {
  private final KafkaTemplate<String, String> template;


  private final String topic;

  @Autowired
  public KedaProducer(KafkaTemplate<String, String> template, @Value("${kafka.topic}") String topic) {
    this.template = template;
    this.topic = topic;
  }


  @Override
  public void run() {
    for (; ; ) {
      String dummyData = String.format("Message : %d", (int) (Math.random() * 100));
      CompletableFuture<SendResult<String, String>> callback = template.send(topic, dummyData);
      SendResult<String, String> result = null;
      try {
        result = callback.get(5, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } catch (ExecutionException e) {
        throw new RuntimeException(e);
      } catch (TimeoutException e) {
        throw new RuntimeException(e);
      }
      long offset = result.getRecordMetadata().offset();
      System.out.println("Produced a record at : " + offset);
    }
  }
}
