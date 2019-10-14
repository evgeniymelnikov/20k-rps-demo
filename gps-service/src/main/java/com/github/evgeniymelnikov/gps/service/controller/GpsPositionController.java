package com.github.evgeniymelnikov.gps.service.controller;

import com.github.evgeniymelnikov.gps.service.dto.GpsPositionInfo;
import com.github.evgeniymelnikov.gps.service.model.GpsPosition;
import com.github.evgeniymelnikov.gps.service.repository.GpsPositionRepository;
import com.github.evgeniymelnikov.gps.service.service.GpsPositionMessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/gps-tracker")
@RequiredArgsConstructor
public class GpsPositionController {

    private final GpsPositionRepository gpsPositionRepository;
    private final GpsPositionMessageHandler gpsPositionMessageHandler;

    @PostMapping
    public void add(@RequestBody Map<String, Object> gpsPositionInfo) {
        if (gpsPositionInfo == null || gpsPositionInfo.isEmpty()) {
            return;
        }
        gpsPositionMessageHandler.sendToHandler(gpsPositionInfo);
    }

    @GetMapping("/{extId}")
    public Flux<GpsPositionInfo> getByExtId(@PathVariable UUID extId) {
        return gpsPositionRepository.findAll(Example.of(new GpsPosition(null, extId, null)))
                .map(GpsPositionInfo::of);
    }
}
