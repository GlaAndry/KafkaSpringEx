package com.glaandry.example.kafkaspring.domaincrawler.configuration;

import com.glaandry.example.kafkaspring.domaincrawler.entity.Domain;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public KafkaTemplate<String, Domain> kafkaTemplate() {
        return new KafkaTemplate<String, Domain>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, Domain> producerFactory() {
        //Creazione manuale della configurazione in modo da serializzare i dati
        //i dati tramite Json e Strings
        Map<String, Object> configs = new HashMap<>();

//        final JsonDeserializer<Domain> valueDeserializer = new JsonDeserializer<>();
//        valueDeserializer.addTrustedPackages("com.glaandry.example.kafkaspring.domaincrawler.entity.Domain");

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configs.put(JsonDeserializer.TRUSTED_PACKAGES, "com.glaandry.example.kafkaspring.domaincrawler.entity.Domain"); //evita errore nella deserializzazione
        return new DefaultKafkaProducerFactory<>(configs);
    }
}
