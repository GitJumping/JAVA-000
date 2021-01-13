package com.example.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

public class KafkaPressureMesure {
    public static void main(String[] args) {
        String servers = "localhost:20003,localhost:20004,localhost:20005";
        String topic = "TestTopic";
        String message = "test";

        KafkaProducer<String, String> producer = KafkaUtils.createProducer(servers);
        KafkaUtils.send(producer, topic, message);

        KafkaConsumer<String, String> consumer = KafkaUtils.createConsumer(servers, topic);
        KafkaUtils.readMessage(consumer, 100);
    }
}
