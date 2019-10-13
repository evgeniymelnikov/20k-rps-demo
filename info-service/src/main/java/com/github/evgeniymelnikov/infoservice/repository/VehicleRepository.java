package com.github.evgeniymelnikov.infoservice.repository;

import com.github.evgeniymelnikov.infoservice.model.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface VehicleRepository extends CrudRepository<Vehicle, UUID> {

    List<Vehicle> findAllByNumberContainingAndVinCodeContaining(String number, String vinCode);
}
