package com.github.evgeniymelnikov.gps.service.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientOptions;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;

@Configuration
public class MongoConfig {

    @Value("${mongodb.host}")
    private String host;

    @Value("${mongodb.port}")
    private int port;

    @Value("${mongodb.database}")
    private String DBName;

    @Bean
    public MongoClientOptions mongoOptions() {
        return MongoClientOptions.builder()
                .threadsAllowedToBlockForConnectionMultiplier(30)
                .build();
    }

//    @Bean
//    public ReactiveMongoClientFactoryBean mongoClient() {
//        ReactiveMongoClientFactoryBean clientFactory = new ReactiveMongoClientFactoryBean();
//        clientFactory.setHost(host);
//        clientFactory.setPort(port);
//        return clientFactory;
//    }

    public @Bean ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory());
    }

    public @Bean ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory() {

        return new SimpleReactiveMongoDatabaseFactory(MongoClients.create(
                new ConnectionString(String.format("mongodb://%s:%s", host, port))),
                DBName);
    }
}
