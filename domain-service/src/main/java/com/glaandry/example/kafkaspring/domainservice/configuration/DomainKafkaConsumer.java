package com.glaandry.example.kafkaspring.domainservice.configuration;

import com.glaandry.example.kafkaspring.domainservice.entity.Domain;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DomainKafkaConsumer {

    @Bean
    public Consumer<KStream<String, Domain>> domainService() {
        //Print Domains & Status
        return kstream -> kstream.foreach((key, domain) -> {
            System.out.println(String.format("Domain consumed [%s] and isDead var is: [%s]", domain.getDomain(), domain.isDead()));
        });
    }

}
