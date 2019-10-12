package com.github.evgeniymelnikov.gps.service.service;

import java.util.Map;

public interface GpsPositionProcessor {

    void addToQueue(Map<String, Object> gpsPositionJson);
}
