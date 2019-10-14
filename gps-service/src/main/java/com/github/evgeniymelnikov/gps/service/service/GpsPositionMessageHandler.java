package com.github.evgeniymelnikov.gps.service.service;

import java.util.Map;

public interface GpsPositionMessageHandler {

    void sendToHandler(Map<String, Object> gpsPositionJson);
}
