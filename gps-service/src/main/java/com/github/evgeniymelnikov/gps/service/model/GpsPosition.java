package com.github.evgeniymelnikov.gps.service.model;

import com.mongodb.client.model.geojson.Point;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(GpsPosition.COLLECTION_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GpsPosition {

    public static final String COLLECTION_NAME = "gpsPosition";

    @Id
    private ObjectId id;

    private UUID extId;

    private List<Double> coordinates;
}
