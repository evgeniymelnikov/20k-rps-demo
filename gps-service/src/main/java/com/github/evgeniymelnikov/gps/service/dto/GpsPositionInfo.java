package com.github.evgeniymelnikov.gps.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.evgeniymelnikov.gps.service.model.GpsPosition;
import com.mongodb.client.model.geojson.Point;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class GpsPositionInfo {

    private UUID extId;
    private Point location;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createTime;

    public UUID getExtId() {
        return extId;
    }

    @JsonProperty("id")
    public void setExtId(UUID extId) {
        this.extId = extId;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static GpsPosition map(@NonNull GpsPositionInfo gpsPositionInfo) {
        return new GpsPosition(new ObjectId(new Date()), gpsPositionInfo.extId, gpsPositionInfo.getLocation());
    }

    public static GpsPositionInfo of(@NonNull GpsPosition gpsPosition) {
        return new GpsPositionInfo(gpsPosition.getExtId(), gpsPosition.getLocation(), gpsPosition.getId().getDate());
    }
}
