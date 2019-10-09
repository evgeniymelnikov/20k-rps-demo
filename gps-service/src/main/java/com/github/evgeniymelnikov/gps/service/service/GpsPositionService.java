package com.github.evgeniymelnikov.gps.service.service;

import java.util.Map;

public interface GpsPositionService {

    void addToQueue(Map<String, Object> gpsPositionJson);
}
