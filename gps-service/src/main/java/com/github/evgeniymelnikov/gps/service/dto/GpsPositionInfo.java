package com.github.evgeniymelnikov.gps.service.dto;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.github.evgeniymelnikov.gps.service.model.GpsPosition;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class GpsPositionInfo {

    private UUID extId;

//    @JsonDeserialize(using = PointDeserializer.class)
//    @JsonSerialize(using = PointSerializer.class)
    private List<Double> coordinates;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createTime;

    public UUID getExtId() {
        return extId;
    }

    @JsonProperty("id")
    public void setExtId(UUID extId) {
        this.extId = extId;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static GpsPosition map(@NonNull GpsPositionInfo gpsPositionInfo) {
        return new GpsPosition(new ObjectId(new Date()), gpsPositionInfo.extId, gpsPositionInfo.getCoordinates());
    }

    public static GpsPositionInfo of(@NonNull GpsPosition gpsPosition) {
        return new GpsPositionInfo(gpsPosition.getExtId(), gpsPosition.getCoordinates(), gpsPosition.getId().getDate());
    }
}
