package com.github.evgeniymelnikov.gps.service.service;

import com.github.evgeniymelnikov.gps.service.model.GpsPosition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GpsPositionServiceImpl implements GpsPositionService {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<GpsPosition> findByExtIdLast200Records(@Nullable UUID extId) {
        if (extId == null) {
            log.trace("для поиска передан null extId");
            return Flux.empty();
        }
        return mongoTemplate.find(
                Query.query(Criteria.where("extId").is(extId))
                        .limit(200).with(Sort.by(Sort.Direction.DESC, "_id")),
                GpsPosition.class,
                GpsPosition.COLLECTION_NAME
        );
    }
}
