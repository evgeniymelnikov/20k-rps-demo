package com.github.evgeniymelnikov.gps.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.evgeniymelnikov.gps.service.model.GpsPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GpsPositionInfo {

    @JsonProperty("id")
    private UUID extId;
    private List<Double> coordinates;
    private Instant createTime;

    public static GpsPositionInfo of(GpsPosition gpsPosition) {
        return new GpsPositionInfo(
                gpsPosition.getExtId(),
                gpsPosition.getCoordinates(),
                Instant.ofEpochSecond((long) gpsPosition.getId().getTimestamp())
        );
    }

    public static List<GpsPositionInfo> of(List<GpsPosition> gpsPositions) {
        return gpsPositions.stream().map(GpsPositionInfo::of).collect(Collectors.toList());
    }
}
