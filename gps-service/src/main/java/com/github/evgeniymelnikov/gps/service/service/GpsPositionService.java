package com.github.evgeniymelnikov.gps.service.service;

import com.github.evgeniymelnikov.gps.service.model.GpsPosition;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface GpsPositionService {

    Flux<GpsPosition> findByExtIdLast200Records(UUID extId);

}
