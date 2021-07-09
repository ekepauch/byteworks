package com.upperlink.fcmb.configuration;


import com.upperlink.fcmb.producerModel.ProducerTransactionProcessing;
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

@EnableKafka
@Configuration
public class TransactionConsumerConfig {

    @Value("${bootstrap.server}")
    private String bootStrapServer;

    @Value("${transaction-group-id}")
    private String transactionGroupId;

    @Bean
    public ConsumerFactory<String,ProducerTransactionProcessing> transactionConsumerFactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG,transactionGroupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(ProducerTransactionProcessing.class,false));


    }


    @Bean
    @ConditionalOnMissingBean(name = "transactionProcessingContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, ProducerTransactionProcessing> transactionProcessingContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ProducerTransactionProcessing> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory();
        kafkaListenerContainerFactory.setConsumerFactory(transactionConsumerFactory());
        return kafkaListenerContainerFactory;
    }





}
