package com.keda.kedademoproducer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class KedaProducerRunner implements CommandLineRunner {

  private final TaskExecutor executor;
  private final KedaProducer producer;

  public KedaProducerRunner(TaskExecutor executor, KedaProducer producer) {
    this.executor = executor;
    this.producer = producer;
  }


  @Override
  public void run(String... args) throws Exception {
    executor.execute(producer);
  }
}
