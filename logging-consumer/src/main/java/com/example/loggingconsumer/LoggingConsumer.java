package com.example.loggingconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
public class LoggingConsumer {
    private final KafkaConsumer<String, String> consumer;

    public LoggingConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapservers,
                           @Value("${logging.topic}") String topic) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapservers);
        properties.put("group.id", "my-group");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singletonList(topic));
        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("Received message: " + record.value());
                    }
                }
            } finally {
                consumer.close();
            }
        });
        consumerThread.start();
    }
}
