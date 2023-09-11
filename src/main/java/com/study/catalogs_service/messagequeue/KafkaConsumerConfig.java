package com.study.catalogs_service.messagequeue;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/* 컨슈머 -> 데이터를 소비하는 역할 (topic을 구독해두고 값이 변경되면 데이터를 읽는 역할) */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String,String> consumerFactory() {

        Map<String,Object> properties = new HashMap<>();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.18.0.101:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"consumerGroupId");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class );  //DESERIALIZER는 SERIALIZER를 풀어주는 것이다.
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class );
        return new DefaultKafkaConsumerFactory<>(properties);

    }


    /* Topic에 변경 사항이 생겼을때 데이터를 읽어들이는 역할 */
    /* 값을 읽어 들이기 때문에 DESERIALIZER를 사용해서 String을 읽어 들인다. */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        return kafkaListenerContainerFactory;
    }

}
