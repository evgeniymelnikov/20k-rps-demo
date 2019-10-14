package com.github.evgeniymelnikov.infoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GpsPositionInfo {

    private UUID id;

    private List<Double> coordinates;

    private Instant createTime;
}
