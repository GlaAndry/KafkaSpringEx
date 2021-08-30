package com.glaandry.example.kafkaspring.domaincrawler.service;

import com.glaandry.example.kafkaspring.domaincrawler.entity.Domain;
import com.glaandry.example.kafkaspring.domaincrawler.entity.DomainList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DomainCrawlerService {

    @Autowired
    private KafkaTemplate<String, Domain> kafkaTemplate;
    private final String KAFKA_TOPIC_NAME = "web-domains";

    public DomainCrawlerService(KafkaTemplate<String, Domain> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void crawl(String name) {
        //Web Client per effettuare la richiesta da API remota
        Mono<DomainList> domainListMono = WebClient.create()
                .get()
                .uri("https://api.domainsdb.info/v1/domains/search?domain=" + name + "&zone=com")
                .accept(MediaType.APPLICATION_JSON) //accettiamo solamente dati JSON
                .retrieve()
                .bodyToMono(DomainList.class);
                //inseriamo i dati in un oggetto di tipo
                //domain List, in quanto l'API restituisce
                //una lista di oggetti Domain

        //pubblicazione dei dati in Kafka in maniera individuale.
        domainListMono.subscribe( domainList -> {
            domainList.getDomains().forEach( domain -> {
                //Pubblicazione del messaggio all'interno del Topic
                kafkaTemplate.send(KAFKA_TOPIC_NAME, domain);
                System.out.println("Domain Message" + domain.getDomain());
            });
        });
    }
}
