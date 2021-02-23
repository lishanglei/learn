package com.example.kafka.day01;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2021/1/20
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2021/1/20              lishanglei      v1.0.0           Created
 */
@Slf4j
public class ProducerFastStart {

    private static final String brokerList = "192.168.5.12:9092";

    private static final String topic = "test";

    public static void main(String[] args) {

        Properties properties = new Properties();
        //设置key序列化器
        //properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //另一种写法
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //设置重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);
        //设置值序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //设置集群地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        //kafka producer线程安全
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "kafka-demo", "hello kafka");
        //同步发送
//        try {
//            Future<RecordMetadata> send = producer.send(record);
//            log.info("发送完成===========================================" + send.get().topic());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            producer.close();


//        }

        //异步发送
        try {
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e == null) {
                        log.info("===============" + metadata.partition() + ":" + metadata.offset());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

}
