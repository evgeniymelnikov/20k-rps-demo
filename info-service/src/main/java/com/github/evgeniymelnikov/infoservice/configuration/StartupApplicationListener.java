package com.github.evgeniymelnikov.infoservice.configuration;

import com.github.evgeniymelnikov.infoservice.model.Vehicle;
import com.github.evgeniymelnikov.infoservice.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final VehicleRepository vehicleRepository;
    private final String predefinedId = "2a87cc8a-e93b-11e9-81b4-2a2ae2dbcce4";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        vehicleRepository.save(new Vehicle(predefinedId, "P509XP", "WBAKP9C55FD933715"));
    }
}
