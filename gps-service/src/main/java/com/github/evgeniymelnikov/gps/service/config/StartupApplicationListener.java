package com.github.evgeniymelnikov.gps.service.config;

import com.github.evgeniymelnikov.gps.service.model.GpsPosition;
import com.github.evgeniymelnikov.gps.service.repository.GpsPositionRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final GpsPositionRepository gpsPositionRepository;
    private final UUID predefinedExtId = UUID.fromString("2a87cc8a-e93b-11e9-81b4-2a2ae2dbcce4");

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        final List<GpsPosition> gpsPositionList = new ArrayList<>(5);
        gpsPositionList.addAll(
                Arrays.asList(
                    new GpsPosition(new ObjectId(new Date()), predefinedExtId, new ArrayList<>(Arrays.asList(0d, 0d))),
                    new GpsPosition(new ObjectId(new Date()), predefinedExtId, new ArrayList<>(Arrays.asList(0.1d, 0d))),
                    new GpsPosition(new ObjectId(new Date()), predefinedExtId, new ArrayList<>(Arrays.asList(0.2d, 0d))),
                    new GpsPosition(new ObjectId(new Date()), predefinedExtId, new ArrayList<>(Arrays.asList(0.3d, 0d))),
                    new GpsPosition(new ObjectId(new Date()), predefinedExtId, new ArrayList<>(Arrays.asList(0.4d, 0.1d)))
                )
        );
        gpsPositionRepository.saveAll(gpsPositionList).subscribe();
//        gpsPositionRepository.findAll(Example.of(new GpsPosition(null, predefinedExtId, null))).subscribe(
//                (data) -> {
//                    Date date = data.getId().getDate();
//                    System.out.println(date);
//                }
//        );
    }
}
