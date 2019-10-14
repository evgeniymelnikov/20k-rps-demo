package com.github.evgeniymelnikov.infoservice.controller;

import com.github.evgeniymelnikov.infoservice.dto.GpsPositionInfo;
import com.github.evgeniymelnikov.infoservice.dto.VehicleInfo;
import com.github.evgeniymelnikov.infoservice.model.Vehicle;
import com.github.evgeniymelnikov.infoservice.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final VehicleRepository vehicleRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${gps.service.url}")
    private String gpsServiceUrl;

    @Value("${gps.service.port}")
    private Integer gpsServicePort;

    @PostMapping("/info")
    public List<Vehicle> getInfo(@RequestBody VehicleInfo vehicleInfo) {
        if (StringUtils.hasText(vehicleInfo.getNumber()) &&  StringUtils.hasText(vehicleInfo.getVinCode())) {
            throw new IllegalArgumentException("В запросе отсутствуют данные для фильтрации.");
        }

        return vehicleRepository.findAllByNumberContainingAndVinCodeContaining(
                vehicleInfo.getNumber() == null ? "" : vehicleInfo.getNumber(),
                vehicleInfo.getVinCode() == null ? "" : vehicleInfo.getVinCode()
        );
    }

    @GetMapping("/{id}/gps-position")
    public List<GpsPositionInfo> getGpsPosition(@PathVariable final String id) {
        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException("Отсутствует id для запроса координат.");
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<GpsPositionInfo>> response = restTemplate.exchange(
                String.format("http://%s:%d/gps-tracker/%s/", gpsServiceUrl, gpsServicePort, id),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GpsPositionInfo>>(){});
        return response.getBody();
    }

}
