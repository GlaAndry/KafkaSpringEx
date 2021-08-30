package com.glaandry.example.kafkaspring.domaincrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomainCrawlerApplication {

	/**
	 * Il Crawler si occupa di andare a produrre le informazioni
	 * da inserire all'interno del topic "web-domains"
	 */
	public static void main(String[] args) {
		SpringApplication.run(DomainCrawlerApplication.class, args);
	}

}
