package com.glaandry.example.kafkaspring.domaincrawler.controller;

import com.glaandry.example.kafkaspring.domaincrawler.service.DomainCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/domain")
//Fetch delle informazioni dalle API web utilizzate
public class DomainCrawlerController {

    private DomainCrawlerService domainCrawlerService;

    public DomainCrawlerController(DomainCrawlerService domainCrawlerService) {
        this.domainCrawlerService = domainCrawlerService;
    }

    @GetMapping("/lookup/{webName}")
    public String lookup(@PathVariable("webName") final String name) {
        domainCrawlerService.crawl(name);
        return "Domain Crawler ha scaricato i dati e li ha inseriti nel topic";
    }
}
