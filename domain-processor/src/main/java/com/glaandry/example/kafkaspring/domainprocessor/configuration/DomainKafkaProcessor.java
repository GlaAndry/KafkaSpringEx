package com.glaandry.example.kafkaspring.domainprocessor.configuration;

import com.glaandry.example.kafkaspring.domaincrowler.entity.Domain;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Function;

@Configuration
public class DomainKafkaProcessor {

    //La definizione del topic e le altre informazioni necessarie a Kafka
    //Sono presenti all'interno del file "application.yml"

    @Bean
    public Function<KStream<String, Domain>, KStream<String, Domain>> domainProcessor() {
        //Tramite kafka stream è possibile eseguire operazioni direttamente sui dati, come
        //Filtraggio in base a determinati parametri.
        return kstream -> kstream.filter((key, domain) -> {
            if(domain.isDead()){
                System.out.println("Domain Dead: " + domain.getDomain());
            } else {
                System.out.println("Domain alive: " + domain.getDomain());
            }
            return !domain.isDead();
        });

    }
}
