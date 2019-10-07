package com.github.evgeniymelnikov.gps.service.repository;

import com.github.evgeniymelnikov.gps.service.model.GpsPosition;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpsPositionRepository extends ReactiveMongoRepository<GpsPosition, ObjectId> {
}
