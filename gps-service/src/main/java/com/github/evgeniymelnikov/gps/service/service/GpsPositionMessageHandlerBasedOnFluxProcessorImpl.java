package com.github.evgeniymelnikov.gps.service.service;

import java.util.Map;

public class GpsPositionMessageHandlerBasedOnFluxProcessorImpl implements GpsPositionMessageHandler {

    @Override
    public void sendToHandler(Map<String, Object> gpsPositionJson) {
        //todo: https://stackoverflow.com/questions/51370463/spring-webflux-flux-how-to-publish-dynamically
        // todo: https://stackoverflow.com/questions/54380668/dynamically-push-events-values-to-a-flux-during-application-run
    }
}
