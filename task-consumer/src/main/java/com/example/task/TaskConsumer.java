package com.example.task;

import com.example.common.RechargingMoneyTask;
import com.example.common.SubTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
public class TaskConsumer {
    private final KafkaConsumer<String, String> consumer;
    private final TaskResultProducer taskResultProducer;
    public TaskConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapservers,
                        @Value("${task.topic}") String topic, TaskResultProducer taskResultProducer) {
        this.taskResultProducer = taskResultProducer;

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
                    ObjectMapper mapper = new ObjectMapper();
                    for (ConsumerRecord<String, String> record : records) {
                        // record: RechargingMoneyTask (jsonString)
                        RechargingMoneyTask task;
                        try {
                            task = mapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                        for (SubTask subTask : task.getSubTaskList()) {
                            subTask.setStatus("success");
                        }

                        this.taskResultProducer.sendTaskResult(task.getTaskID(), task);

                    }
                }
            } finally {
                consumer.close();
            }
        });
        consumerThread.start();
    }
}
