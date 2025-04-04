package com.travelagency.travelagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.travelagency.controller", "com.travelagency.service"})
@EntityScan("com.travelagency.model")
@EnableJpaRepositories("com.travelagency.repository")
public class TravelagencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelagencyApplication.class, args);
    }
}