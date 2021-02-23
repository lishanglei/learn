package com.example.kafka.day01;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2021/1/21
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2021/1/21              lishanglei      v1.0.0           Created
 */
public class ConsumeFastStart {

    private static final String brokerList = "192.168.5.12:9092";

    private static final String topic = "test";

    private static final String groupId = "group.demo";

    public static void main(String[] args) {

        Properties properties = new Properties();
        //设置key序列化器
        //properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //设置值序列化器
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        KafkaConsumer<String, String> consumer = new KafkaConsumer(properties);
        consumer.subscribe(Collections.singletonList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }
    }
}
