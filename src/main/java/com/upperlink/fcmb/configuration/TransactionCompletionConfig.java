package com.upperlink.fcmb.configuration;



import com.upperlink.fcmb.producerModel.ProducerTransactionProcessing;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TransactionCompletionConfig {

    @Value("${bootstrap.server}")
    private String bootStrapServer;

    @Bean
    public ProducerFactory<String,ProducerTransactionProcessing> transactionCompletionProducerFactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(config);
    }

    @Bean
    public KafkaTemplate<String, ProducerTransactionProcessing> transactionCompletionProducerTemplate(){
        return new KafkaTemplate<String, ProducerTransactionProcessing>(transactionCompletionProducerFactory());
    }
}
