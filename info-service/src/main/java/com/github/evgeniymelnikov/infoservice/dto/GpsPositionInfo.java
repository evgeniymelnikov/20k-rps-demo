package com.github.evgeniymelnikov.infoservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class GpsPositionInfo {

    private UUID extId;

    private List<Double> coordinates;

    private Timestamp createTime;
}
