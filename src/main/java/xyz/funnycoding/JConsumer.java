package xyz.funnycoding;

import lombok.extern.java.Log;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Log
public class JConsumer {

    CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "${kafka.topic}")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        log.info(String.format("received payload='%s'", consumerRecord.toString()));
        latch.countDown();
    }
}
