package com.github.evgeniymelnikov.gps.service.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientOptions;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

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

    public @Bean ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory());
    }

    public @Bean ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory() {

        return new SimpleReactiveMongoDatabaseFactory(MongoClients.create(
                new ConnectionString(String.format("mongodb://%s:%s", host, port))),
                DBName);
    }

//    @Bean
//    public MongoCustomConversions customConversions() {
//        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
//        converters.add(new TimeZoneReadConverter());
//        converters.add(new TimeZoneReadConverter());
//        return new MongoCustomConversions(converters);
//    }
//
//    @Bean
//    public MappingMongoConverter mongoConverter() throws Exception {
//        MongoMappingContext mappingContext = new MongoMappingContext();
//        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
//        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
//        mongoConverter.setCustomConversions(customConversions());
//        return mongoConverter;
//    }
}
