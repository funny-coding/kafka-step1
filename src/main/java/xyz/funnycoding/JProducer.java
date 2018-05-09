package xyz.funnycoding;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static xyz.funnycoding.config.JProducerConfig.topic;

@Log
@Component
public class JProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String payload) {
        log.info(String.format("sending payload='%s' to topic='%s'", payload, topic));
        kafkaTemplate.send(topic, payload);
    }
}
