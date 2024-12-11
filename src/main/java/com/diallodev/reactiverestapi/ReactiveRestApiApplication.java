package com.diallodev.reactiverestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveRestApiApplication.class, args);
    }


}
