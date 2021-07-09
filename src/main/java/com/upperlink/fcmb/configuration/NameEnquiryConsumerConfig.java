package com.upperlink.fcmb.configuration;


import com.upperlink.fcmb.producerModel.ProducerNameEnquiry;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
@EnableKafka
@Configuration
public class NameEnquiryConsumerConfig {

    @Value("${bootstrap.server}")
    private String bootStrapServer;

    @Value("${name-group-id}")
    private String nameEnquiryGroupId;

    @Bean
    public ConsumerFactory<String,ProducerNameEnquiry> consumerFactory(){

        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG,nameEnquiryGroupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(ProducerNameEnquiry.class,false));


    }


    @Bean
    @ConditionalOnMissingBean(name = "containerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, ProducerNameEnquiry> containerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ProducerNameEnquiry> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        return kafkaListenerContainerFactory;
    }


}
