package com.example.common;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class LoggingProducer {
    private final KafkaProducer<String, String> producer;

    private final String topic;

    public LoggingProducer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServcers,
                           @Value("${logging.topic}") String topic) {

        Properties properties = new Properties();

        properties.put("bootstrap.servers", bootstrapServcers);

        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(properties);
        this.topic = topic;
    }

    public void sendMessage(String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record, ((recordMetadata, exception) -> {
                    if (exception == null) {

                    } else {
                        exception.printStackTrace();
                    }
                })
        );
    }
}
