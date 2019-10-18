package com.github.evgeniymelnikov.gps.service.controller;

import com.github.evgeniymelnikov.gps.service.dto.GpsPositionInfo;
import com.github.evgeniymelnikov.gps.service.service.GpsPositionMessageHandler;
import com.github.evgeniymelnikov.gps.service.service.GpsPositionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/gps-tracker")
public class GpsPositionController {

    private final GpsPositionMessageHandler gpsPositionMessageHandler;
    private final GpsPositionService gpsPositionService;

    public GpsPositionController(
        @Qualifier("gpsPositionHandlerFlux") GpsPositionMessageHandler gpsPositionMessageHandler,
        GpsPositionService gpsPositionService
    ) {
        this.gpsPositionMessageHandler = gpsPositionMessageHandler;
        this.gpsPositionService = gpsPositionService;
    }

    @PostMapping
    public void add(@RequestBody Map<String, Object> gpsPositionInfo) {
        if (gpsPositionInfo == null || gpsPositionInfo.isEmpty()) {
            return;
        }
        gpsPositionMessageHandler.sendToHandler(gpsPositionInfo);
    }

    @GetMapping("/{extId}")
    public Flux<GpsPositionInfo> getByExtId(@PathVariable UUID extId) {
        return gpsPositionService.findByExtIdLast200Records(extId)
                .map(GpsPositionInfo::of);
    }
}
