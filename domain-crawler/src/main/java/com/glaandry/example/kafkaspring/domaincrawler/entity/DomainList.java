package com.glaandry.example.kafkaspring.domaincrawler.entity;

import java.util.List;

public class DomainList {

    List<Domain> domains;

    public DomainList(List<Domain> domains) {
        this.domains = domains;
    }

    public DomainList() {
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }
}
