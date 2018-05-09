package xyz.funnycoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringKafkaApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringKafkaApplication.class, args);

        JProducer producer = context.getBean(JProducer.class);
        JConsumer consumer = context.getBean(JConsumer.class);

        producer.send("Just saying hello :) ");
        consumer.latch.await(10, TimeUnit.SECONDS);
        context.close();
    }

}
